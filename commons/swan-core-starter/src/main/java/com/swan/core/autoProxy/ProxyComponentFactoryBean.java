package com.swan.core.autoProxy;

import com.swan.core.scanner.BaseFactoryBean;

import java.lang.reflect.Proxy;

/** ProxyComponent 动态代理生成工厂 <br/>
 *     1) FactoryBean 中不能使用@Autowired 注入spring组件, 需要借助于 ApplicationContextAware 接口
 * @author zongf
 * @since 2020-11-26
 */
public class ProxyComponentFactoryBean extends BaseFactoryBean {

    @Override
    public Object getObject() throws Exception {

        // 校验类型
        if (!type.isInterface()) {
            throw new UnsupportedOperationException("@ProxyComponent 只能修饰接口!");
        }

        // 获取注解属性
        ProxyComponentDefaultHandler invocationHandler = type.getAnnotation(ProxyComponent.class).invokerClass().newInstance();

        // 设置容器上下文
        invocationHandler.setApplicationContext(applicationContext);

        return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, invocationHandler);
    }

}
