package com.swan.mail.core;

import cn.hutool.core.util.ObjectUtil;
import com.swan.freemarker.core.FreemarkerTemplate;
import com.swan.mail.config.SwanMailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zongf
 * @date 2021-10-31
 */
@Slf4j
public class MailSender implements IMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FreemarkerTemplate freemarkerTemplate;

    @Autowired
    @Qualifier("freemarkerTemplateInside")
    private FreemarkerTemplate freemarkerTemplateInside;

    @Autowired
    private SwanMailProperties swanMailProperties;

    @Autowired
    private MailProperties mailProperties;

    @Override
    public boolean sendTextMail(String title, List<String> toList, List<String> ccList, String content) {
        return sendMail(title, toList, ccList, content, 0,false);
    }

    @Override
    public boolean sendHtmlMail(String title, List<String> toList, List<String> ccList, String content) {
        return sendMail(title, toList, ccList, content, 0, true);
    }

    @Override
    public boolean sendHtmlMail(String title, List<String> toList, List<String> ccList, String content, List<MailAttachment> attachmentList) {
        Map<String, Object> root = new HashMap<>();
        root.put("title", title);
        root.put("toList", toList);
        root.put("ccList", ccList);
        root.put("content", content);
        root.put("attachmentList", attachmentList);

        String htmlContent = null;
        if (ObjectUtil.isNotNull(swanMailProperties) && ObjectUtil.isNotEmpty(swanMailProperties.getHtmlTemplate())) {
            // 自定义 html 模板
            htmlContent = this.freemarkerTemplate.getContent(swanMailProperties.getHtmlTemplate(), root);
        }else {
            // 使用内置的 html 模板
            htmlContent = this.freemarkerTemplateInside.getContent("inside_templates/mail/mail.ftl", root);
        }

        return sendMail(title, toList, ccList, htmlContent, attachmentList.size(), true);
    }

    private boolean sendMail(String title, List<String> toList, List<String> ccList, String content, int attachmentSize, Boolean isHtml) {
        try {
            // 转换收件人
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setSentDate(new Date());
            helper.setFrom(mailProperties.getUsername(), swanMailProperties.getSenderName());
            helper.setSubject(title);
            helper.setTo((String[]) toList.toArray());

            if (ObjectUtil.isNotEmpty(ccList)) {
                helper.setCc((String[]) ccList.toArray());
            }
            helper.setText(content, isHtml);

            // 发送邮件
            if (swanMailProperties.isTest()) {
                StringBuilder sb = new StringBuilder();
                sb.append("标题:").append(title)
                    .append("发件人:").append(toList)
                    .append("抄送人:").append(ccList)
                    .append("附件数:").append(attachmentSize)
                    .append("邮件内容:").append(content);
                log.info("[模拟邮件发送]\n%s", sb.toString());
            }else {
                // 发送邮件
                javaMailSender.send(message);
            }

            log.info("[邮件发送成功] 邮件类型:{}, 标题:{}, 收件人:{}, 抄送人:{}, 附件数量:{}",
                    isHtml?"文本邮件":"html邮件", title, toList, ccList, attachmentSize);
            return true;
        } catch (Exception ex) {
            String message = String.format("[邮件发送失败] 邮件类型:%s, 标题:%s, 收件人:%s, 抄送人:%s, 附件数量:{}",
                    isHtml?"文本邮件":"html邮件", title, toList, ccList, attachmentSize);
            log.warn(message, ex);
        }
        return false;
    }

}
