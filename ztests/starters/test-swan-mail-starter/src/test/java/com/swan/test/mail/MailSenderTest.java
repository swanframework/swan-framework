package com.swan.test.mail;

import com.swan.freemarker.core.IFreemarkerTemplate;
import com.swan.mail.core.IMailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

@ActiveProfiles("dev")
@SpringBootTest
class MailSenderTest {

    @Autowired
    private IMailSender mailSender;

    @Test
    public void sendMail() {
        List<String> toList = Arrays.asList("3154007684@qq.com");
        List<String> ccList = Arrays.asList("3154007684@qq.com");

        mailSender.sendHtmlMail("订单报警", toList, ccList,
                "你好，您的邮件验证码为:123456", new ArrayList<>());
    }


}
