package com.swan.mybatis.mapper.field.handler;

import java.lang.reflect.Field;

/** @ForceNull 字段处理器
 * @author zongf
 * @date 2021-01-08
 */
public class ForceNullFieldHandler extends AbsFieldHandler {

    public ForceNullFieldHandler(Field field) {
        super(field);
    }

    @Override
    public Object newValue(Object object, Object oldValue) {
        return null;
    }

}
