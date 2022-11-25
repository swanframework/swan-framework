package com.swan.mybatis.mapper.field.handler;

import com.swan.mybatis.mapper.field.IAutoFieldGenerator;

import java.lang.reflect.Field;

/** @AutoTime 字段处理器
 * @author zongf
 * @date 2021-01-08
 */
public class AutoFieldHandler extends AbsFieldHandler {

    private IAutoFieldGenerator autoFieldSuppler;

    public AutoFieldHandler(Field field,  IAutoFieldGenerator autoFieldSuppler) {
        super(field);
        this.autoFieldSuppler = autoFieldSuppler;
    }

    @Override
    public Object newValue(Object object, Object oldValue) {
        return autoFieldSuppler.generateValue();
    }

}