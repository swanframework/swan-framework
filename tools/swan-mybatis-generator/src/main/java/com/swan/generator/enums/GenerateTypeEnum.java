package com.swan.generator.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author zongf
 * @since 2021-03-25
 */
public enum GenerateTypeEnum {

	/** 1:entity */
    ENTITY(1, "entity"),

	/** 2:condition */
    CONDITION(2, "condition"),

	/** 3:mapper */
    MAPPER(3, "mapper"),

    ;

    private Integer code;

    private String desc;

    GenerateTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举对象的code属性
     *
     * @return int 枚举code
     * @author zongf
     * @since 2021-03-25
     */
    public int code() {
        return this.code;
    }

    /**
     * 获取枚举对象的desc属性
     *
     * @return String 枚举desc
     * @author zongf
     * @since 2021-03-25
     */
    public String desc() {
        return this.desc;
    }

    /**
     * 获取code对应的枚举类型, 如果不存在则返回null
     *
     * @param code 枚举code
     * @return ClassType code对应的枚举类型
     * @author zongf
     * @since 2021-03-25
     */
    public static GenerateTypeEnum getEnumOfCode(int code) {
        return Arrays.stream(GenerateTypeEnum.values())
                .filter(enm -> code == enm.code)
                .findFirst().orElse(null);
    }

    /**
     * 获取desc对应的枚举类型, 如果不存在则返回null
     *
     * @param desc 枚举desc
     * @return ClassType desc对应的枚举类型
     * @author zongf
     * @since 2021-03-25
     */
    public static GenerateTypeEnum getEnumOfDesc(String desc) {
        return Arrays.stream(GenerateTypeEnum.values())
                .filter(enm -> enm.desc.equals(desc))
                .findFirst().orElse(null);
    }

    /**
     * 获取code对应的desc. 如果code不存在，则返回null
     *
     * @param code 枚举code
     * @return String 枚举desc 或 null
     * @author zongf
     * @since 2021-03-25
     */
    public static String getDesc(int code) {
        Optional<GenerateTypeEnum> optional = Arrays.stream(GenerateTypeEnum.values())
                .filter(enm -> code == enm.code)
                .findFirst();
        return optional.isPresent() ? optional.get().desc : null;
    }

    /**
     * 获取code对应的code. 如果code不存在，则返回null
     *
     * @param desc 枚举desc
     * @return Integer 枚举code 或 null
     * @author zongf
     * @since 2021-03-25
     */
    public static Integer getCode(String desc) {
        Optional<GenerateTypeEnum> optional = Arrays.stream(GenerateTypeEnum.values())
                .filter(enm -> enm.desc.equals(desc))
                .findFirst();
        return optional.isPresent() ? optional.get().code : null;
    }

}
