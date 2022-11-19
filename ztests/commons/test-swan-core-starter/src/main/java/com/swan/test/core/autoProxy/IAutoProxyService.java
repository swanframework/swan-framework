package com.swan.test.core.autoProxy;

import com.swan.core.autoProxy.ProxyComponent;

/**
 * @author zongf
 * @date 2020-11-26
 */
@ProxyComponent(invokerClass = MyAutoProxyHandler.class)
public interface IAutoProxyService {

    public Integer add(int a, int b);

}
