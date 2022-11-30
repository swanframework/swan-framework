package com.swan.mail.listener;

import com.swan.core.listener.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** 邮件事件
 * @author zongf
 * @since 2022-11-30
 **/
@Setter @Getter
public class MailEvent extends Event {

    /** 邮件标题 */
    private String title;

    /** 收件人列表 */
    private List<String> toList;

    /** 抄送人列表 */
    private List<String> ccList;

    /** 邮件内容 */
    private String content;

    /** 附件数量 */
    private Integer attachmentSize;

    /** html 类型邮件 */
    private Boolean isHtml;

    /** 是否发送成功 */
    private Boolean sendStatus;

}
