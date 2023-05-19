package com.swan.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.*;
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

    /** 对list中的元素进行分组
     * @param list 列表
     * @param function 获取分组key的方法
     * @param <K> 分组 key
     * @param <V> list元素类型
     * @return Map<K, List<V>>
     */
    public static <K, V> Map<K, List<V>> groupToMap(List<V> list, Function<V, K> function) {
        if (list == null) {
            return null;
        }
        Map<K, List<V>> map = new HashMap<>();
        for (V item : list) {
            List<V> values = map.computeIfAbsent(function.apply(item), lst -> new ArrayList());
            values.add(item);
        }
        return map;
    }

    /** 对list中的元素转换为map
     * @param list 列表
     * @param function 获取key的方法
     * @param <K> key 类型
     * @param <V> list元素类型
     * @return Map<K, List<V>>
     */
    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> function) {
        if (list == null) {
            return null;
        }
        Map<K, V> map = new HashMap<>();
        for (V item : list) {
            map.put(function.apply(item), item);
        }
        return map;
    }

    @Data
    @AllArgsConstructor
    static class TestPO{
        private Integer id;

        private String name;
    }


    public static void main(String[] args) {
        List<TestPO> list = new ArrayList<>();
        list.add(new TestPO(1, "a"));
        list.add(new TestPO(1, "aa"));
        list.add(new TestPO(1, "aaa"));
        list.add(new TestPO(1, "aaaa"));
        list.add(new TestPO(2, "b"));
        list.add(new TestPO(2, "bb"));
        list.add(new TestPO(2, "bbb"));
        list.add(new TestPO(3, "c"));
        list.add(new TestPO(3, "cc"));
        list.add(new TestPO(3, "ccc"));

        Map<Integer, List<TestPO>> map = groupToMap(list, TestPO::getId);

        map.forEach((key, value) -> System.out.println(key + ":" + value));



    }



}
