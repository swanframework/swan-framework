package com.swan.env.core;

import java.util.List;

public interface ISwanEnvironment {

    /** 应用名称 */
    public String applicationName();

    /** 激活的 profiles */
    public List<String> activeProfiles();

    /** web 服务端口号*/
    public Integer serverPort();

    /** cpu 核心数 */
    public Integer cpuCores();

}
