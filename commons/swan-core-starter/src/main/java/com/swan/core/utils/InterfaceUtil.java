package com.swan.core.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** 接口工具
 * @author zongf
 * @since 2021-02-26
 */
public class InterfaceUtil {

    /** 获取接口的所有接口
     * @param type 接口类型
     * @return Set<Class>
     * @author zongf
     * @since 2021-02-26
     */
    public static Set<Class> getInterfaces(Class type) {

        Set<Class> set = new HashSet<>();
        set.add(type);

        //递归处理父接口
        Class[] interfaces = type.getInterfaces();
        for (Class anInterface : interfaces) {
            set.addAll(getInterfaces(anInterface));
        }

        return set;
    }

    /** 判断 sun 接口是否是 parent 的直接或间接子接口
     * @param parentInterface 直接或间接父接口
     * @param sunInterface 子接口
     * @return boolean
     * @author zongf
     * @since 2021-02-26
     */
    public static boolean hasInheritance(Class parentInterface, Class sunInterface) {
        return getInterfaces(sunInterface).contains(parentInterface);
    }

    /** 当 subInterface 直接或间接继承 parentInterface 时, 获取声明继承关系的类.
     *  如: D extends B extends A , 那么结果为 B
     * @param subInterface 子接口
     * @param parentInterface 父接口
     * @return Class
     * @author zongf
     * @since 2021-02-26
     */
    public static Class findDeclareInheritance(Class subInterface, Class parentInterface) {

        Class[] interfaces = subInterface.getInterfaces();
        if (Arrays.asList(interfaces).contains(parentInterface)) {
            return subInterface;
        }else {
            for (Class anInterface : subInterface.getInterfaces()) {
                return findDeclareInheritance(anInterface, parentInterface);
            }
        }
        return null;
    }

}
