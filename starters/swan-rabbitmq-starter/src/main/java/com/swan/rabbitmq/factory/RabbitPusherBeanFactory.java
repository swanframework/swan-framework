package com.swan.rabbitmq.factory;

import com.swan.core.invoker.DefaultMethodInvoker;
import com.swan.core.invoker.IMethodInvoker;
import com.swan.core.invoker.InterfaceInvocationHandler;
import com.swan.core.scanner.BaseFactoryBean;
import com.swan.core.utils.MethodHandleUtil;
import com.swan.rabbitmq.reflect.RabbitMethodInvoker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/** RabbitPusher 动态代理生成工厂 <br/>
 *     1) FactoryBean 中不能使用@Autowired 注入spring组件, 需要借助于 ApplicationContextAware 接口
 *     2) 代理接口中方法时, 需要注意忽略非public方法和静态方法
 * @author zongf
 * @since 2020-09-07
 */
public class RabbitPusherBeanFactory extends BaseFactoryBean {

    @Override
    public Object getObject() throws Exception {

        String publisherConfirmType = applicationContext.getEnvironment().getProperty("spring.rabbitmq.publisher-confirm-type");

        Map<Method, IMethodInvoker> methodInvokerMap = new HashMap<>();

        // 获取代理接口中所有方法, 包括从父接口中定义的方法
        for (Method declaredMethod : type.getMethods()) {

            // 忽略静态和非public方法
            int modifiers = declaredMethod.getModifiers();
            if (Modifier.isStatic(modifiers) || !Modifier.isPublic(modifiers)) {
                continue;
            }

            // 如果时Object中的方法, 则忽略
            if (Object.class.equals(declaredMethod.getDeclaringClass())) {
                continue;
            }else if (declaredMethod.isDefault()) {
                // 如果方法是[接口默认方法],则创建默认方法代理
                IMethodInvoker methodInvoker = new DefaultMethodInvoker(MethodHandleUtil.getMethodHandleJava(declaredMethod));
                methodInvokerMap.put(declaredMethod, methodInvoker);
            }else {
                // 如果方法为普通方法, 则创建Rabbit 方代理
                IMethodInvoker methodInvoker = new RabbitMethodInvoker(this.applicationContext.getBean(RabbitTemplate.class), publisherConfirmType);
                methodInvokerMap.put(declaredMethod, methodInvoker);
            }
        }

        // 创建接口代理
        InvocationHandler invocationHandler = new InterfaceInvocationHandler(methodInvokerMap);

        //这里主要是创建接口对应的实例，便于注入到spring容器中
        return (Object) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, invocationHandler);
    }

}
