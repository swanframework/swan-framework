package com.swan.core.utils;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author zongf
 * @since 2021-03-01
 */
public class DateUtil {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static String yyyyMMdd = "yyyy-MM-dd";

    /** 获取当前日期
     * @return String
     * @author zongf
     * @since 2021-03-01
     */
    public static String getToday(){
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        return sdf.format(new Date());
    }

    public static String format(Date date, String pattern) {
        if (Objects.isNull(date)) {
            return null;
        }
        if (ObjectUtil.isEmpty(pattern)) {
            pattern = yyyyMMdd;
        }
        return new SimpleDateFormat(pattern).format(date);
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
