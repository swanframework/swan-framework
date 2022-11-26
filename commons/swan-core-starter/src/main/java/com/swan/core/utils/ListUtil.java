package com.swan.core.utils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.function.Function;

/**
 * @author zongf
 * @since 2022-11-26
 **/
public class ListUtil {

    /** 转换为数组
     * @param list
     * @param <T>
     * @return 数组
     */
    public static <T> T[] toArray(List<T> list, Class<T> clz) {
        if (list == null) {
            return null;
        }
        T[] array = (T[]) Array.newInstance(clz, list.size());
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }


        return array;
    }

    /** 转换为数组，修改类型
     * @param list 列表
     * @param clz 目标数组类型
     * @param function 类型 T 转换为 R 的方法
     * @param <T> 列表中数据类型
     * @param <R> 转换后数组的类型
     * @return R 的数组
     * @author zongf
     * @since 2022-11-26
     */
    public static <T, R> R[] toArray(List<T> list, Class<R> clz, Function<T, R> function) {
        if (list == null) {
            return null;
        }

        R[] array = (R[]) Array.newInstance(clz, list.size());
        for (int i = 0; i < list.size(); i++) {
            array[i] = function.apply(list.get(i));
        }
        return array;
    }


}
