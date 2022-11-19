package com.swan.core.utils;

import java.io.Closeable;

/** IO 工具类
 * @author zongf
 * @since 2019-11-30
 */
public class IOUtils {

    /** 批量关闭流
     * @param closeables
     * @author zongf
     * @since 2019-11-30
     */
    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if(closeable != null){
                    closeable.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
