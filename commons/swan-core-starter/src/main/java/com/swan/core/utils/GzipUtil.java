package com.swan.core.utils;


import com.swan.core.exception.SwanBaseException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/** Gzip 压缩/解压缩工具类
 * @author zongf
 * @date 2021-05-14
 */
public final class GzipUtil {

    /** 压缩字节
     * @param bytes
     * @return byte[]
     * @author zongf
     * @date 2021-05-14
     */
    public static final byte[] compress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        //将byte数据读入文件流
        ByteArrayOutputStream bos = null;
        GZIPOutputStream gzipos = null;
        try {
            bos = new ByteArrayOutputStream();
            gzipos = new GZIPOutputStream(bos);
            gzipos.write(bytes);
        } catch (Exception e) {
            throw new SwanBaseException("Gzip 压缩异常");
        } finally {
            IOUtils.close(gzipos, bos);
        }
        return bos.toByteArray();
    }

    /**
     * @param bytes Gzip压缩的字节数组
     * @return byte[]
     * @description 解压缩字节数组
     */
    public static final byte[] unCompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteAos = null;
        ByteArrayInputStream byteArrayIn = null;
        GZIPInputStream gzipIn = null;
        try {
            byteArrayIn = new ByteArrayInputStream(bytes);
            gzipIn = new GZIPInputStream(byteArrayIn);
            byteAos = new ByteArrayOutputStream();
            byte[] b = new byte[4096];
            int temp = -1;
            while ((temp = gzipIn.read(b)) > 0) {
                byteAos.write(b, 0, temp);
            }
        } catch (Exception e) {
            throw new SwanBaseException("Gzip 解压异常");
        } finally {
            IOUtils.close(byteAos, gzipIn, byteArrayIn);
        }
        return byteAos.toByteArray();
    }

}
