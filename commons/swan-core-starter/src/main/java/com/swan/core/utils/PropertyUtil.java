package com.swan.core.utils;


import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @since 2022-11-12
 **/
public class PropertyUtil {

    /** 获取属性值或默认值。如果属性值为 null，则返回 defaultValue
     * @param getter 属性方法
     * @param defaultValue 默认值
     * @param <T> 属性类型
     * @return T
     */
    public static <T> T getOrDefault(Supplier<T> getter, T defaultValue) {
        T result = getter.get();

        return Objects.nonNull(result) ? result : defaultValue;
    }

    /** 获取属性值或默认值 <br/>
     *  * 如果属性值为 notExpect, 则返回 defaultValue <br/>
     *  * 如果属性值不等于 notExpect, 则返回 属性值 <br/>
     * @param getter 获取属性方法
     * @param notExpect 非期望值
     * @param defaultValue 默认值
     * @param <T> 属性类型
     * @return T
     * @since 2022-11-12
     */
    public static <T> T getOrDefault(Supplier<T> getter, T notExpect, T defaultValue) {
        T result = getter.get();

        if (Objects.isNull(notExpect)) {
            return Objects.nonNull(result) ? result : defaultValue;
        } else {
            return notExpect.equals(result) ? defaultValue : result;
        }
    }


    /** 设置非空属性<br/>
     *   * 如果 value 为空，则不执行 setter 方法 <br/>
     *   * 如果 value 不为空, 则执行 setter 方法 <br/>
     * @param setter setter 方法
     * @param value 属性值
     * @param <T>
     */
    public static <T> void setNotNull(Consumer<T> setter, T value) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        }
    }

}
