package com.swan.mybatis.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;

/** 雪花id
 * @author zongf
 * @date 2020-11-19
 */
public class SnowIdUtil {

    private static final Snowflake SNOWFLAKE;

    static {
        SNOWFLAKE = IdUtil.getSnowflake( RandomUtil.randomInt(30));
    }

    /** 生成雪花id
     * @return long 雪花id
     * @author zongf
     * @date 2021-07-08
     */
    public static Long generateId() {
        return SNOWFLAKE.nextId();
    }

}
