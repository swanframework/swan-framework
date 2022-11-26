package com.swan.core.utils;

import com.swan.core.exception.SwanBaseException;

/** 类加载工具
 * @author zongf
 * @since 2022-11-26
 **/
public class ClassUtil {


    public static Class forName(String className, ClassLoader classLoader) {
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new SwanBaseException("类加载异常:" + className, e);
        }
    }

}
