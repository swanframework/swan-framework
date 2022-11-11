package com.swan.mybatis.field.handler;

import com.swan.mybatis.enums.IdGeneratorType;
import com.swan.mybatis.field.id.IdGenerator;
import com.swan.mybatis.field.id.IdGeneratorFactory;

import java.lang.reflect.Field;

/** id 字段处理器
 * @author zongf
 * @date 2021-01-08
 */
public class IdFieldHandler extends AbsFieldHandler {

    /** id 生成方式 */
    private IdGeneratorType idGeneratorType;

    /** 自定义id 生成器 */
    private Class<? extends IdGenerator> customerIdGenerator;

    public IdFieldHandler(Field field, IdGeneratorType idGeneratorType, Class<? extends IdGenerator> idGenerator) {
        super(field);
        this.idGeneratorType = idGeneratorType;
        this.customerIdGenerator = idGenerator;
    }

    @Override
    public Object newValue(Object object, Object oldValue) {
        return oldValue != null ? oldValue : IdGeneratorFactory.getIdGenerator(idGeneratorType, customerIdGenerator).generateId();
    }

}
