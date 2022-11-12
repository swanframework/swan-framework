package com.swan.mybatis.util;

import java.io.*;

/**文本文件读写, 当文件内容过大时, 需要考虑内存
 * @author zongf
 * @date 2021-01-11
 */
public class TxtFileUtil {

    /** 写入文件
     * @param filePath 文件路径
     * @param contents 文件内容
     * @author zongf
     * @date 2021-01-11
     */
    public static void writeFile(String filePath, String... contents ) {

        File file = new File(filePath);

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
            for (String content : contents) {
                bw.write(content);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(bw);
        }
    }

}