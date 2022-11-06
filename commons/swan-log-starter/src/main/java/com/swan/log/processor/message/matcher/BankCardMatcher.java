package com.swan.log.processor.message.matcher;

/** 银行卡匹配
 * @author zongf
 * @since 2022-11-07
 **/
public class BankCardMatcher implements ISensitiveMatcher {

    @Override
    public String regex() {
        // [^\d]: 非数字
        // (\d{4}): 前四位数字 = $1
        // [^\d]{8,11}: 中间8-11位数字
        // (\d{4}): 末尾四位数字 = $2
        // [^\d]: 非数字
        // [^\\d](\\d{16})[^\\d]
        return "[^\\d]?(\\d{4})\\d{8,11}(\\d{4})[^\\d]";
    }

    @Override
    public String replacement() {
        return "$1****$2";
    }

}
