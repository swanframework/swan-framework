package com.swan.core.autoProxy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** ProxyComponent 默认invoker
 * @author zongf
 * @since 2020-11-26
 */
@Setter @Getter
public class ProxyComponentDefaultHandler implements InvocationHandler {

    private ApplicationContext applicationContext;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

}
