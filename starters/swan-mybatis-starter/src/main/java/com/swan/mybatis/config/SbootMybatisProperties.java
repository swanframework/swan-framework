package com.swan.mybatis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zongf
 * @date 2021-07-11
 */
@ConfigurationProperties(prefix = "sboot.mybatis")
@Setter @Getter
public class SbootMybatisProperties {

    private LogConfig log = new LogConfig();

    @Setter @Getter
    public static class LogConfig{

        private boolean enable;

        private String path = "";

        private Map<String, Boolean> mapperConfig = new HashMap<>();

    }

}
