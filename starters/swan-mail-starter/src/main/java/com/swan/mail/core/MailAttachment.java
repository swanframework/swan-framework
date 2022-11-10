package com.swan.mail.core;

import lombok.Getter;
import lombok.Setter;

/** 邮件附件
 * @author zongf
 * @date 2021-10-31
 */
@Setter @Getter
public class MailAttachment {

    // 名称
    private String name;

    // 连接地址
    private String link;

}
