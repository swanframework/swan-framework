package com.swan.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

/** 反射工具类
 * @author zongf
 * @since 2021-01-07
 */
public class ReflectUtil {

    /***
     * 获取类/接口所实现的所有接口, 包括:
     *  1) 当前类实现的接口
     *  2) 父类实现的接口, 递归
     *  3) 父接口实现的接口, 递归
     * @param clz
     * @return Set<Class>
     * @author zongf
     * @since 2020-01-07
     */
    public static Set<Class> getAllInterfaces(Class clz){
        Set<Class> allInterfaces = new LinkedHashSet<>();

        // 如果有父类, 则递归处理父类接口
        if (clz.getSuperclass() != null) {
            Set<Class> subList = getAllInterfaces(clz.getSuperclass());
            allInterfaces.addAll(subList);
        }

        // 如果当前类/接口, 实现了接口
        Class[] interfaces = clz.getInterfaces();
        allInterfaces.addAll(Arrays.asList(interfaces));

        // 递归处理接口
        if (interfaces.length != 0) {
            for (Class anInterface : interfaces) {
                Set<Class> subInterfaces = getAllInterfaces(anInterface);
                allInterfaces.addAll(subInterfaces);
            }
        }

        return allInterfaces;
    }

    /** 判断类/接口是否实现了某个接口
     * @param sub 类或接口
     * @param parentInterface
     * @return boolean
     * @author zongf
     * @since 2020-01-07
     */
    public static boolean isImplement(Class sub, Class parentInterface) {
        // 如果是接口, 才进行判断, 否则直接抛出异常
        if (parentInterface.isInterface()) {
            Set<Class> allInterfaces = getAllInterfaces(sub);
            return allInterfaces.contains(parentInterface);
        }
        return false;
    }

    // 获取最底层的继承类
    private static Class getFirstInheritanceClass(Class[] interfaces, Class supperInterface){
        Class result = null;

        // 逐级向上遍历
        for (Class anInterface : interfaces) {
            if (InterfaceUtil.hasInheritance(supperInterface, anInterface)) {
                TypeVariable[] typeParameters = anInterface.getTypeParameters();
                if (typeParameters.length == 2) {
                    if (typeParameters[0].getName().equals("ID") && typeParameters[1].getName().equals("E")) {
                        result = anInterface;
                    }
                }
            }
        }

        if (result == null) {
            for (Class anInterface : interfaces) {
                result = getFirstInheritanceClass(anInterface.getInterfaces(), supperInterface);
            }
        }
        return result;
    }

    /** 获取类/接口直接或间接实现的泛型接口的参数类型, 示例:
     *  A 直接或间接实现了接口 B<Integer, String>, 则 clz为A.class, targetInterface为B.class, 返回结果为 [Integer.class, String.class]
     * @param clz 类或接口
     * @param supperInterface 目标类型
     * @return Type[]

     */
    public static Type[] getRawParamTypes(Class clz, Class supperInterface) {

        Type[] paramTypes = null;

        // 如果目标接口不是接口类型, 则返回null
        if (!supperInterface.isInterface()) {
            return null;
        }

        // 获取当前接口类型
        Class[] interfaces = clz.getInterfaces();

        // 获取目标类型
        Class targetInterface = getFirstInheritanceClass(interfaces, supperInterface);

        if (targetInterface != null) {
            clz = InterfaceUtil.findDeclareInheritance(clz, targetInterface);

            // 如果当前类声明实现的接口包含目标接口, 则直接获取当前类实现所有接口的详情
            Type[] types = clz.getGenericInterfaces();

            for (Type type : types) {
                // 如果接口类型为泛型类型
                if (type instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) type;
                    // 如果为目标类型, 则处理
                    if (pType.getRawType().equals(targetInterface)) {
                        paramTypes = pType.getActualTypeArguments();
                    }
                }
            }
        }else {
            // 如果当前类未显示声明目标类型

            // 先尝试从父类递归向上找目标类型
            if (paramTypes == null && clz.getSuperclass() != null) {
                paramTypes = getRawParamTypes(clz.getSuperclass(), supperInterface);
            }

            // 如果依然未找到, 则借助接口向上递归找类型
            if (paramTypes == null) {
                for (Class anInterface : interfaces) {
                    paramTypes = getRawParamTypes(anInterface, supperInterface);
                    if(paramTypes != null) break;
                }
            }
        }
        return paramTypes;
    }

    /** 获取字段属性值
     * @param object 对象
     * @param fieldName 属性
     * @return Object
     * @author zongf
     * @since 2021-01-09
     */
    public static Object getFieldValue(Object object, String fieldName) {
        if(object == null || fieldName == null || fieldName.isEmpty()) return null;
        Field declaredField = null;
        boolean needCloseAccessible = false;
        try {

            declaredField = object.getClass().getDeclaredField(fieldName);
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
                needCloseAccessible = true;
            }
            return declaredField.get(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (declaredField != null && needCloseAccessible) {
                declaredField.setAccessible(false);
            }
        }
        return null;
    }

    /** 获取类所有声明的字段，包含父类声明的
     * @param clz 类型
     * @return ArrayList<Field>
     * @author zongf
     * @since 2021-03-01
     */
    public static List<Field> getAllDeclareFields(Class clz) {
        LinkedHashMap<String, Field> fieldMap = new LinkedHashMap<>();
        getAllDeclareFields(fieldMap, clz);
        return new ArrayList<Field>(fieldMap.values());
    }


    private static void getAllDeclareFields(LinkedHashMap<String, Field> map, Class clz){
        // 添加当前类定义的属性
        for (Field declaredField : clz.getDeclaredFields()) {
            // 如果子类已重写, 则无需重复添加
            if (!map.containsKey(declaredField.getName())) {
                map.put(declaredField.getName(), declaredField);
            }
        }

        if (!Object.class.equals(clz.getSuperclass())) {
            getAllDeclareFields(map, clz.getSuperclass());
        }
    }


    public static Object getFieldValue(Object object, Field field) {
        if (Objects.isNull(field)) {
            return null;
        }

        if (!field.isAccessible()) {
            field.setAccessible(true);
        }


        try {
            return field.get(object);
        } catch (Exception ex) {

        }
        return null;
    }

    public static <T> T newInstance(Class<T> clz) {

        try {
           return clz.newInstance();

        } catch (Exception e) {

        }
        return null;
    }

    public static void setField(Object target, Field field, Object fieldValue) {
        try {
            field.set(target, fieldValue);
        } catch (Exception e) {
        }
    }
}
