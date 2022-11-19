package com.swan.core.invoker;

import java.lang.reflect.Method;

/** 接口默认方法调用器
 * @author zongf
 * @since 2020-11-19
 */
public interface IMethodInvoker {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
