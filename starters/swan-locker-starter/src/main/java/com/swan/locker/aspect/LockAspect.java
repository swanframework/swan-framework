package com.swan.locker.aspect;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.swan.locker.anno.Lock;
import com.swan.locker.core.ILocker;
import com.swan.locker.exception.LockException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**  限流切面
 * @author zongf
 * @since 2022-06-16
 **/
@Slf4j
@Aspect
public class LockAspect implements EmbeddedValueResolverAware, Ordered, BeanFactoryAware {

    @Autowired
    private ILocker locker;

    protected BeanFactory beanFactory;

    protected StringValueResolver embeddedValueResolver;

    private LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();;

    protected SpelExpressionParser methodParamParser = new SpelExpressionParser();

    /** 缓存方法参数表达式  */
    private Map<String, Expression> methodParamExprMap = new HashMap<>();

    @Pointcut("@annotation(com.swan.locker.anno.Lock)")
    protected void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        // 获取 lock 配置信息
        Lock lockAnno = getMethodAnno(joinPoint, Lock.class);

        // 计算所表达式的值
        Object keyValue = calcParamExprValue(joinPoint, lockAnno);
        String jsonValue = JSONObject.toJSONString(keyValue);

        String md5Value = Objects.nonNull(jsonValue) ? SecureUtil.md5(jsonValue) : null;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String lockKey = String.format("%s:%s:%s", lockAnno.keyPrefix(), methodSignature.getMethod().getName(), md5Value);

        // 尝试加锁
        boolean lockSuccess = this.locker.tryLock(lockKey, jsonValue, lockAnno.time(), lockAnno.retryTimes(), lockAnno.retryInterval());
        log.info("并发上锁,锁定结果:{},logKey:{}, lockValue:{}", lockSuccess, lockKey, jsonValue);

        // 加锁失败，抛出异常
        if (!lockSuccess) {
            throw new LockException(lockAnno.failedCode(), lockAnno.failedMsg());
        }

        try {
            // 调用目标方法
            return joinPoint.proceed(joinPoint.getArgs());
        }  finally {
            if (lockSuccess) {
                this.locker.deleteLock(lockKey);
            }
        }

    }

    /**
     * 计算参数表达式的值
     * @param joinPoint 切入点
     * @param lock 注解
     * @author zongf
     * @since 2022-06-13
     */
    public Object calcParamExprValue(ProceedingJoinPoint joinPoint, Lock lock) {
        if(lock.keyExpr().isEmpty()) return null;

        Expression methodParamExpr = methodParamExprMap.computeIfAbsent(lock.keyExpr(), key -> methodParamParser.parseExpression(lock.keyExpr()));

        // 每一次请求都重新构建上下文
        EvaluationContext methodParamContext = buildMethodContext(joinPoint);
        return methodParamExpr.getValue(methodParamContext);
    }

    /**
     *  构建方法参数解析上下文
     * @param joinPoint 切入点
     * @author zongf
     * @since 2022-06-13
     */
    protected EvaluationContext buildMethodContext(ProceedingJoinPoint joinPoint) {
        EvaluationContext context = new StandardEvaluationContext();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] parameterValues = joinPoint.getArgs();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(methodSignature.getMethod());

        // 校验参数不为空，且参数名称和参数值数量一致
        if (parameterNames == null || parameterValues == null || parameterValues.length != parameterNames.length) {
            return context;
        }

        // 构造上下文
        for (int i = 0; i < parameterValues.length; i++) {
            context.setVariable(parameterNames[i], parameterValues[i]);
        }

        return context;
    }

    /**
     * 获取方法上的注解
     * @param joinPoint 切入点
     * @param anno 注解类型
     * @author zongf
     * @since 2022-06-13
     */
    protected <T extends Annotation> T getMethodAnno(ProceedingJoinPoint joinPoint, Class<T> anno) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(anno);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.embeddedValueResolver = resolver;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
