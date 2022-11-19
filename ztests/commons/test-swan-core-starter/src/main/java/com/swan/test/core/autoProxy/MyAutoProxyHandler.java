package com.swan.test.core.autoProxy;

import com.swan.core.autoProxy.ProxyComponentDefaultHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;

/** 自定义代理
 * @author zongf
 * @date 2020-11-26
 */
@Slf4j
public class MyAutoProxyHandler extends ProxyComponentDefaultHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("method:{}, args:{}, proxy:{}", method.getName(), Arrays.asList(args), proxy.getClass().toString());
        Integer a = (Integer) args[0];
        Integer b = (Integer) args[1];
        return a + b;
    }

}
