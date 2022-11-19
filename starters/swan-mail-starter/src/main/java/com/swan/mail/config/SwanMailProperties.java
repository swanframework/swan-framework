package com.swan.mail.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zongf
 * @since 2021-07-11
 */
@ConfigurationProperties(prefix = "spring.mail.sender")
@Setter
@Getter
public class SwanMailProperties {

    /** 自定义 html 模板
     *    1) 如果不指定的话，需确保未自定义 spring.freemarker.template-loader-path
     *    2) 如果修改了template-loader-path, 则必须自定义此
     * */
    private String htmlTemplate;

    /** 发件人昵称: 不设置的话, 默认显示账号 */
    private String name;

    /** 测试模式: 测试模式不真正发送邮件, 只打印日志 */
    private boolean test;

}
