package com.swan.mybatis.util;

import java.io.Closeable;

/** IO 工具类
 * @author zongf
 * @date 2019-11-30
 */
public class IOUtils {

    /** 批量关闭流
     * @param closeables
     * @author zongf
     * @date 2019-11-30
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
