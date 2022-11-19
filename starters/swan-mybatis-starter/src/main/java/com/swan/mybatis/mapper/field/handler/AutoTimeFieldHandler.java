package com.swan.mybatis.mapper.field.handler;

import java.lang.reflect.Field;
import java.util.Date;

/** @AutoTime 字段处理器
 * @author zongf
 * @since 2021-01-08
 */
public class AutoTimeFieldHandler extends AbsFieldHandler {

    public AutoTimeFieldHandler(Field field) {
        super(field);
    }

    @Override
    public Object newValue(Object object, Object oldValue) {
        return new Date();
    }

}
