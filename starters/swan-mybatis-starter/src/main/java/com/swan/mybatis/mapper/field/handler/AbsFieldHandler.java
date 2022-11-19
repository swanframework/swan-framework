package com.swan.mybatis.mapper.field.handler;

import java.lang.reflect.Field;

/** 抽象字段处理器
 * @author zongf
 * @date 2021-01-08
 */
public abstract class AbsFieldHandler {

    /** 字段 */
    protected Field field;

    public AbsFieldHandler(Field field) {
        this.field = field;
    }

    /** 更新字段值
     * @param object 要修改的对象
     * @author zongf
     * @date 2021-01-08
     */
    public final void updateField(Object object){
        //直接设置为可访问，否则在多线程中会抛出异常
        field.setAccessible(true);
        try {
            Object oldValue = field.get(object);
            Object value = newValue(object, oldValue);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /** 字段新值
     * @param object 要修改的对象
     * @param oldValue 原对象
     * @author zongf
     * @date 2021-01-08
     */
    protected abstract Object newValue(Object object, Object oldValue);

}
