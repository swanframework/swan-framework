package com.swan.kaptcha.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zongf
 * @since 2022-12-14
 **/
@Setter @Getter
@ConfigurationProperties(prefix = "swan.kaptcha")
public class KaptchaProperties {



}
