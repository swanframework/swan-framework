package com.swan.core.invoker;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

/** 默认方法执行器
 * @author zongf
 * @since 2020-11-19
 */
public class DefaultMethodInvoker implements IMethodInvoker {

    private final MethodHandle methodHandle;

    public DefaultMethodInvoker(MethodHandle methodHandle) {
      super();
      this.methodHandle = methodHandle;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return methodHandle.bindTo(proxy).invokeWithArguments(args);
    }
}