package com.swan.generator.enums;

import java.util.Arrays;

/**
 * @author zongf
 * @date 2021-11-02
 */
public enum CommentTypeEnum {

    NONE(0, "无注释"),
    DOC(1, "java doc 注释"),
    SWAGGER(2, "swagger 注释"),
    DOC_AND_SWAGGER(3, "java doc 和 Swagger 注释"),
    ;

    private Integer code;

    private String desc;

    CommentTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

    /**
     * 获取 code 对应的枚举, 不存在时, 返回null
     *
     * @param code
     * @return CommentType
     * @author zongf
     * @date 2021-11-02
     */
    public static CommentTypeEnum getEnumByCode(Integer code) {
        return code == null ? null : Arrays.stream(CommentTypeEnum.values())
                .filter(enm -> code.equals(enm.code))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取 desc 对应的 枚举, 不存在时返回 null
     *
     * @param desc
     * @return CommentType
     * @author zongf
     * @date 2021-11-02
     */
    public static CommentTypeEnum getEnumByDesc(String desc) {
        return desc == null ? null : Arrays.stream(CommentTypeEnum.values())
                .filter(enm -> desc.equals(enm.desc))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取 code 对应的 desc, 不存在时返回 null
     *
     * @param code
     * @return String
     * @author zongf
     * @date 2021-11-02
     */
    public static String getDesc(Integer code) {
        CommentTypeEnum enm = getEnumByCode(code);
        return enm == null ? null : enm.desc;
    }

    /**
     * 获取 desc 对应的 code, 不存在时返回 null
     *
     * @param desc
     * @return Integer
     * @author zongf
     * @date 2021-11-02
     */
    public static Integer getCode(String desc) {
        CommentTypeEnum enm = getEnumByDesc(desc);
        return enm == null ? null : enm.code;
    }

}