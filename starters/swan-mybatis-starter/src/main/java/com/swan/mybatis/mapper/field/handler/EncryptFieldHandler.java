package com.swan.mybatis.mapper.field.handler;

import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.core.exception.SwanBaseException;
import com.swan.mybatis.mapper.field.encrypt.EncryptorFactory;
import com.swan.mybatis.mapper.field.encrypt.IEncryptor;

import java.lang.reflect.Field;

/** @AutoTime 字段处理器
 * @author zongf
 * @since 2021-01-08
 */
public class EncryptFieldHandler extends AbsFieldHandler {

    private Class<? extends IEncryptor>  encryptor;

    private boolean toUpper;

    public EncryptFieldHandler(Field field, Class<? extends IEncryptor>  encryptor, boolean toUpper) {
        super(field);
        this.encryptor = encryptor;
        this.toUpper = toUpper;
    }

    @Override
    public Object newValue(Object object, Object oldValue) {
        if (oldValue == null) {
            return null;
        }
        if (oldValue instanceof String) {
            String encrypt = EncryptorFactory.getEncryptor(encryptor).encrypt((String) oldValue);
            return toUpper ? encrypt.toUpperCase() : encrypt;
        } else {
          throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "字段加密异常");
        }
    }

}
