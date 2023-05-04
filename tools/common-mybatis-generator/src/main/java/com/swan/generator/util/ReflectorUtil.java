package com.swan.generator.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/** 反射工具
 * @author zongf
 * @since 2021-03-01
 */
public class ReflectorUtil {


    /** 获取类及其父类所有声明的属性名
     * @param clz 类型
     * @return ArrayList<Field>
     * @author zongf
     * @since 2021-03-01
     */
    public static Set<String> getAllDeclareFields(Class clz) {

        Set<String> fieldNameList = Arrays.stream(clz.getDeclaredFields()).map(Field::getName).collect(Collectors.toSet());

        if (!Object.class.equals(clz.getSuperclass())) {
            fieldNameList.addAll(getAllDeclareFields(clz.getSuperclass()));
        }
        return fieldNameList;
    }


}
