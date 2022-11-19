package com.swan.core.yaml;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zongf
 * @since 2021-12-01
 */
@Setter @Getter
@ConfigurationProperties(prefix = "sboot.yaml-source")
public class YamlSourceProperties {

    /** 是否开启 YamlSource 解析*/
    private boolean enable;

}
