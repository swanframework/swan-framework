package com.swan.log.processor.message.matcher;

/** 手机号匹配
 * @author zongf
 * @since 2022-11-07
 **/
public class PhoneMatcher implements ISensitiveMatcher {
    @Override
    public String regex() {
        return "[^\\d]1(\\d{2})\\d+(\\d{4})";
    }

    @Override
    public String replacement() {
        return "1$1****$2";
    }
}
