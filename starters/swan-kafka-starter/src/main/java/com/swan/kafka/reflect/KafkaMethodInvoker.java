package com.swan.kafka.reflect;

import com.swan.core.invoker.IMethodInvoker;
import com.swan.core.utils.JacksonUtil;
import com.swan.core.utils.SnowIdUtil;
import com.swan.kafka.anno.KafkaParam;
import com.swan.kafka.anno.KafkaPush;
import com.swan.kafka.beans.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Kafka 推送
 *
 * @author zongf
 * @since 2020-11-19
 */
@Slf4j
public class KafkaMethodInvoker implements IMethodInvoker {

    private KafkaTemplate kafkaTemplate;

    private ExpressionParser parser;

    // 方法参数名解析器
    private DefaultParameterNameDiscoverer parameterNameDiscoverer;

    public KafkaMethodInvoker(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.parser = new SpelExpressionParser();
        this.parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 获取注解信息
        KafkaPush kafkaPush = method.getAnnotation(KafkaPush.class);

        // 解析body
        Object body = this.parseParams(method, args);

        // 解析主题名称
        String topic = parseTopic(kafkaPush, method, args);

        try {

            if (kafkaPush.wrapped()) {

                // 构造消息体
                KafkaMessage kafkaMessage = null;
                kafkaMessage = new KafkaMessage<>();
                kafkaMessage.setMessageId(SnowIdUtil.generateId());
                kafkaMessage.setCreateTime(System.currentTimeMillis());
                kafkaMessage.setBody(body);

                body = kafkaMessage;
            }

            String json = JacksonUtil.toString(body);
            log.info("kafka 消息推送, topic:{}, 消息体:{}", topic, json);
            return kafkaTemplate.send(topic, json);
        } catch (Exception ex) {
            log.error("kafka 消息推送, 请求参数:{}, 失败", JacksonUtil.toString(body), ex);
        }
        return null;
    }

    /**
     * 解析 spel 表达式
     */
    private Object getSpel(String spel, Method method, Object[] args) {
        Expression expression = parser.parseExpression(spel);

        EvaluationContext context = new StandardEvaluationContext();
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context);
    }

    /**
     * 解析 topic 名称
     */
    private String parseTopic(KafkaPush kafkaPush, Method method, Object[] args) {
        // 如果未指定 topicSuffix, 则直接返回 topic 定义的名称
        if (!StringUtils.hasText(kafkaPush.topicSuffix())) {
            return kafkaPush.topic();
        }

        // 尝试使用 spel 解析 topicSuffix
        Object topicSuffix = getSpel(kafkaPush.topicSuffix(), method, args);

        // 拼接topic
        return kafkaPush.topic() + kafkaPush.separator() + topicSuffix;
    }


    private Object parseParams(Method method, Object[] args) {
        // 解析参数
        int paramSize = method.getParameters().length;
        if (paramSize == 0) {
            log.warn("@KafkaPush 消息推送注解用法错误, 方法无参数, 方法签名: {}", method.toString());
            throw new RuntimeException("用法错误!");
        } else if (paramSize == 1) {
            return args[0];
        } else {
            // 解析消息体参数
            Map<String, Object> bodyMap = new LinkedHashMap<>(args.length);

            String[] paramNames = parameterNameDiscoverer.getParameterNames(method);

            for (int i = 0; i < method.getParameters().length; i++) {
                Parameter parameter = method.getParameters()[i];
                if (parameter.isAnnotationPresent(KafkaParam.class)) {
                    String paramName = parameter.getAnnotation(KafkaParam.class).value();
                    if (StringUtils.hasText(paramName)) {
                        // 优先使用自定义名称
                        bodyMap.put(paramName, args[i]);
                    } else {
                        // 如果未自定义名称，则尝试获取源码中名称
                        bodyMap.put(paramNames[i], args[0]);
                    }
                }
            }
            if (bodyMap.size() == 0) {
                log.warn("@KafkaPush 多个参数时，至少需要一个@KafkaParam 修饰, 方法签名: {}", method.toString());
                throw new RuntimeException("用法错误!");
            } else if (bodyMap.size() == 1) {
                return bodyMap.entrySet().iterator().next().getValue();
            }
            return bodyMap;
        }
    }

}
