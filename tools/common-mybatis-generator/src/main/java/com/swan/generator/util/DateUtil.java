package com.swan.generator.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zongf
 * @since 2021-03-01
 */
public class DateUtil {

    /** 获取当前日期
     * @return String
     * @author zongf
     * @since 2021-03-01
     */
    public static String getToday(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
