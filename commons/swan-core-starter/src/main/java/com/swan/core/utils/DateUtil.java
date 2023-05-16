package com.swan.core.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zongf
 * @since 2021-03-01
 */
public class DateUtil {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /** 获取当前日期
     * @return String
     * @author zongf
     * @since 2021-03-01
     */
    public static String getToday(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }


}
