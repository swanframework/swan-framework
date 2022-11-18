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

    /** 输出自动生成的 xxxmapper.xml 文件 */
    private LogMapper logMapper = new LogMapper();

    @Setter @Getter
    public static class LogMapper {

        /** 是否开启 */
        private boolean enable;

        /** 输出路径 */
        private String path = "";

        /** 指定生成 xml 文件的 mapper 简单类名*/
        private List<String> mappers;

    }

}
