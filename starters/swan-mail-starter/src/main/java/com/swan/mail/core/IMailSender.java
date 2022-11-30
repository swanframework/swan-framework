package com.swan.mail.core;

import java.util.List;

/** 邮件发送
 * @author zongf
 * @since 2021-10-31
 */
public interface IMailSender {

    /** 发送纯文本邮件
     * @param title 邮件标题, 不能为空
     * @param toList 收件人列表, 不能为空
     * @param ccList 抄送人列表
     * @param content 邮件内容
     * @return boolean
     * @author zongf
     * @since 2021-07-23
     */
    boolean sendTextMail(String title, List<String> toList, List<String>  ccList, String content);

    /** 发送 html 类型邮件
     * @param title 邮件标题, 不能为空
     * @param toList 收件人列表, 不能为空
     * @param ccList 抄送人列表,
     * @param content 邮件内容
     * @return boolean
     * @author zongf
     * @since 2021-07-23
     */
    boolean sendHtmlMail(String title, List<String> toList, List<String>  ccList, String content);

    /** 发送 html 类型邮件
     * @param title 邮件标题， 不能为空
     * @param toList 收件人列表, 不能为空
     * @param ccList 抄送人列表
     * @param content 邮件内容
     * @param attachmentList 附件列表
     * @return boolean
     * @author zongf
     * @since 2021-07-23
     */
    boolean sendHtmlMail(String title, List<String> toList, List<String>  ccList, String content, List<MailAttachment> attachmentList);


    boolean sendMail(String title, List<String> toList, List<String> ccList, String content, int attachmentSize, Boolean isHtml);
}
