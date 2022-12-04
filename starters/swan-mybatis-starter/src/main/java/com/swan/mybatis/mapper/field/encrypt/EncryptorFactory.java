package com.swan.mybatis.mapper.field.encrypt;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zongf
 * @since 2021-01-08
 */
public class EncryptorFactory {

    /** 缓存加密机 */
    private static final Map<Class<? extends IEncryptor>, IEncryptor> CACHE = new HashMap<>();

    /** 获取加密机
     * @param encryptorClz
     * @return
     */
    public static IEncryptor getEncryptor(Class<? extends IEncryptor> encryptorClz) {

        return CACHE.computeIfAbsent(encryptorClz, k -> newInstance(encryptorClz));
    }

    private static IEncryptor newInstance(Class<? extends IEncryptor> clz){
        try {
            return clz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
