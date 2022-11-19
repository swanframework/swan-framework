package com.swan.test.core.yaml;

import com.swan.core.yaml.YamlSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zongf
 * @since 2022-11-19
 **/
@Data
@Component
@ConfigurationProperties(prefix = "jdbc")
@YamlSource(value = "${env}/jdbc.yml")  // 实现配置文件按文件夹分组，不能使用 spring.profiles.active 属性，需要使用自定义属性
public class JdbcYaml {

    private String username;

    private String password;
}
