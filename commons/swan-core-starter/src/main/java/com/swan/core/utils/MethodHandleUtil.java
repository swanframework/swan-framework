package com.swan.core.utils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/** 借鉴mybatis 的 MapperProxy
 * @author zongf
 * @since 2020-11-19
 */
public class MethodHandleUtil {

    private static final Method privateLookupInMethod;

    private static final Constructor<MethodHandles.Lookup> lookupConstructor;

    private static final int ALLOWED_MODES = MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
            | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC;

    static {

        Method privateLookupIn;
        try {
            privateLookupIn = MethodHandles.class.getMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
        } catch (NoSuchMethodException e) {
            privateLookupIn = null;
        }
        privateLookupInMethod = privateLookupIn;

        Constructor<MethodHandles.Lookup> lookup = null;
        if (privateLookupInMethod == null) {
            try {
                lookup = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
                lookup.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("There is neither 'privateLookupIn(Class, Lookup)' nor 'Lookup(Class, int)' method in java.lang.invoke.MethodHandles.", e);
            } catch (Exception e) {
                lookup = null;
            }
        }
        lookupConstructor = lookup;
    }

    /** 获取java 的MethodHandle
     * @param method
     * @throws Exception
     * @return MethodHandle
     * @author zongf
     * @since 2020-11-19
     */
    public static MethodHandle getMethodHandleJava(Method method){
        try {
            if (privateLookupInMethod == null) {
                return getMethodHandleJava8(method);
            } else {
                return getMethodHandleJava9(method);
            }
        } catch (Exception ex) {
            throw new RuntimeException("", ex);
        }
    }

    /** 获取java9 的MethodHandle
     * @param method
     * @throws Exception
     * @return MethodHandle
     * @author zongf
     * @since 2020-11-19
     */
    private static MethodHandle getMethodHandleJava9(Method method) throws Exception {
        final Class<?> declaringClass = method.getDeclaringClass();
        return ((MethodHandles.Lookup) privateLookupInMethod.invoke(null, declaringClass, MethodHandles.lookup())).findSpecial(
                declaringClass, method.getName(), MethodType.methodType(method.getReturnType(), method.getParameterTypes()),
                declaringClass);
    }

    /** 获取java8 的MethodHandle
     * @param method
     * @throws Exception
     * @return MethodHandle
     * @author zongf
     * @since 2020-11-19
     */
    private static MethodHandle getMethodHandleJava8(Method method) throws Exception{
        final Class<?> declaringClass = method.getDeclaringClass();
        return lookupConstructor.newInstance(declaringClass, ALLOWED_MODES).unreflectSpecial(method, declaringClass);
    }

}
