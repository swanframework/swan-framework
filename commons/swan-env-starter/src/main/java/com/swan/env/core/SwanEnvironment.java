package com.swan.env.core;

import java.util.List;

public interface SwanEnvironment {

    /** 应用名称 */
    public String getApplicationName();

    /** 激活的 profiles */
    public List<String> getActiveProfiles();

    /** web 服务端口号*/
    public Integer getServerPort();

    /** cpu 核心数 */
    public Integer getCpuCores();

}
