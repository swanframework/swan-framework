package com.swan.core.utils;

import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.core.exception.SwanBaseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**文本文件读写, 当文件内容过大时, 需要考虑内存
 * @author zongf
 * @since 2021-01-11
 */
public class TxtFileUtil {

    /** 写入文件
     * @param filePath 文件路径
     * @param contents 文件内容
     * @author zongf
     * @since 2021-01-11
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
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "文件写入失败, 文件名:" + filePath, e);
        }finally {
            IOUtils.close(bw);
        }
    }

    /** 写入文件
     * @param filePath 文件路径
     * @param lines 文件内容
     * @author zongf
     * @since 2021-01-11
     */
    public static void writeFile(String filePath, List<String> lines) {
        String[] strings = ListUtil.toArray(lines, String.class);
        writeFile(filePath, strings);
    }

    /** 读取文件内容
     * @param filePath 文件路径
     * @return 文件内容
     * @author zongf
     * @since 2022-11-26
     */
    public static List<String> readFile(String filePath) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));

            List<String> lines = new ArrayList<>();

            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "读取文件失败, 文件名:" + filePath, e);
        }finally {
            IOUtils.close(br);
        }
    }


}