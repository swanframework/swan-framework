package com.swan.mybatis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author zongf
 * @date 2021-07-11
 */
@ConfigurationProperties(prefix = "mybatis")
@Setter @Getter
public class SwanMybatisProperties {

    private LogMapper logMapper = new LogMapper();

    @Setter @Getter
    public static class LogMapper {

        private boolean enable;

        private String path = "";

        private List<String> mappers;

    }

}
