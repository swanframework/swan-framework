package com.swan.log.processor.message.matcher;

/** 身份证匹配
 * @author zongf
 * @since 2022-11-07
 **/
public class IdCardMatcher implements ISensitiveMatcher {
    @Override
    public String regex() {
        return "(\\d{4})\\d+(\\d{4})";
    }

    @Override
    public String replacement() {
        return "$1****$2";
    }
}
