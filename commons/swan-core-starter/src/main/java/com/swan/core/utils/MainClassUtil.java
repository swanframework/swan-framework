package com.swan.core.utils;

import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.core.exception.SwanBaseException;

/** 启动类, 需要注意的是, 当使用单元测试框架时(如junit),返回结果为junit 的启动类, 和预期不一致
 * @author zongf
 * @since 2020-12-11
 */
public final class MainClassUtil {

    /** 获取应用启动类
     * @return Class
     * @author zongf
     * @since 2020-12-11
     */
    public static final Class<?> getMainClass() {
        try {
            StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if ("main".equals(stackTraceElement.getMethodName())) {
                    return Class.forName(stackTraceElement.getClassName());
                }
            }
        }
        catch (ClassNotFoundException ex) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "未找到启动类", ex);
        }
        return null;
    }

    /** 获取启动类所在包
     * @return Class
     * @author zongf
     * @since 2020-12-11
     */
    public static final String getMainClassPackage(){

        Class<?> mainClass = getMainClass();

        if (mainClass != null) {
            String mainClassName = mainClass.getName();
            int lastDotIndex = mainClassName.lastIndexOf(".");
            return (lastDotIndex != -1 ? mainClassName.substring(0, lastDotIndex) : "");
        }

        return null;
    }

}
