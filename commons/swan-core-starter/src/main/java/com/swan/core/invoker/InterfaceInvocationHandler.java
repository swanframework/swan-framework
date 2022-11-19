package com.swan.core.invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/** 接口代理
 * @author zongf
 * @since 2020-11-19
 */
public class InterfaceInvocationHandler implements InvocationHandler {

    //方法代理
    private Map<Method, IMethodInvoker> methodInvokerMap;

    public InterfaceInvocationHandler(Map<Method, IMethodInvoker> dispatch) {
        this.methodInvokerMap = dispatch;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        // Object中定义的方法, 直接调用
        if (method.getDeclaringClass().equals(Object.class)) {
            return method.invoke(this, args);
        }

        // 转发处理
        return methodInvokerMap.get(method).invoke(proxy, method, args);
    }
}