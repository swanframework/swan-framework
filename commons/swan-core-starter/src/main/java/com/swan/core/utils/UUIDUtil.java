package com.swan.core.utils;

import java.util.UUID;

/** uuid 字符串
 * @author zongf
 * @date 2021-05-14
 */
public final class UUIDUtil {

    /** 随机生成 uuid 字符串
     * @return String
     * @author zongf
     * @date 2021-05-14
     */
    public static final String random() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
