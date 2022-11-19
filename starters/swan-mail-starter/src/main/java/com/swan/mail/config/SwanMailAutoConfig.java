package com.swan.mail.config;

import com.swan.core.exception.SwanBaseException;
import com.swan.mail.core.IMailSender;
import com.swan.mail.core.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StringUtils;

/** 邮件 自动化配置
 * @author zongf
 * @since 2021-07-29
 */
@EnableConfigurationProperties({MailProperties.class, SwanMailProperties.class})
@Configuration
public class SwanMailAutoConfig {

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public IMailSender mailSender() {
        return new MailSender();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        // 校验参数
        checkConfig();

        // 设置配置
        applySbootMailProperties(javaMailSender);
        return javaMailSender;
    }


    // 设置参数
    private void applySbootMailProperties(JavaMailSenderImpl javaMailSender) {
        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setPassword(mailProperties.getPassword());
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setDefaultEncoding(mailProperties.getDefaultEncoding().toString());
        javaMailSender.setProtocol(mailProperties.getProtocol());
        if (mailProperties.getPort() != null) {
            javaMailSender.setPort(mailProperties.getPort());
        }
    }

    // 校验必传配置
    private void checkConfig() {
        // 校验参数
        if (!StringUtils.hasText(mailProperties.getHost())) {
            throw new SwanBaseException("配置异常: spring.mail.host 不能为空");
        }
        if (!StringUtils.hasText(mailProperties.getUsername())) {
            throw new SwanBaseException("配置异常: spring.mail.username 不能为空");
        }
        if (!StringUtils.hasText(mailProperties.getPassword())) {
            throw new SwanBaseException("配置异常: spring.mail.password 不能为空");
        }
    }


}
