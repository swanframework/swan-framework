package com.swan.log.processor.message.matcher;

/** 邮箱匹配
 * @author zongf
 * @since 2022-11-07
 **/
public class EmailMatcher implements ISensitiveMatcher {
    @Override
    public String regex() {
        return "([A-Za-z_0-9]{1,2})[A-Za-z_0-9]{1,64}@([A-Za-z1-9_-]+.[A-Za-z]{2,10})";
    }

    @Override
    public String replacement() {
        return "$1***@$2";
    }

    public static void main(String[] args) {
        String mail = "abacDef@qq.com";
        EmailMatcher emailPattern = new EmailMatcher();
        System.out.println(mail.replaceAll(emailPattern.regex(), emailPattern.replacement()));
    }
}
