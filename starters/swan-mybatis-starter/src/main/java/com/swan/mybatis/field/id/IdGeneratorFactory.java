package com.swan.mybatis.field.id;

import com.swan.mybatis.enums.IdGeneratorType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zongf
 * @date 2021-01-08
 */
public class IdGeneratorFactory {

    /** 缓存主键生成器实例 */
    private static final Map<Class<? extends IdGenerator>, IdGenerator> ID_GENERATOR_CACHE = new HashMap<>();

    /** 获取主键生成器
     * @param generatorType
     * @param generator
     * @return
     */
    public static IdGenerator getIdGenerator(IdGeneratorType generatorType, Class<? extends IdGenerator> generator) {

        IdGenerator idGenerator = null;

        switch (generatorType) {
            case AUTO_INC:
                idGenerator = ID_GENERATOR_CACHE.computeIfAbsent(AutoIncIdGenerator.class, k -> new AutoIncIdGenerator());
                break;
            case SNOWID:
                idGenerator = ID_GENERATOR_CACHE.computeIfAbsent(AutoIncIdGenerator.class, k -> new SnowIdGenerator());
                break;
            case UUID:
                idGenerator = ID_GENERATOR_CACHE.computeIfAbsent(AutoIncIdGenerator.class, k -> new UUIdGenerator());
                break;
            case CUSTOMER:
                idGenerator = ID_GENERATOR_CACHE.computeIfAbsent(AutoIncIdGenerator.class, k -> newInstance(generator));
                break;
        }
        return idGenerator;
    }

    private static IdGenerator newInstance(Class<? extends IdGenerator> clz){
        try {
            return clz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
