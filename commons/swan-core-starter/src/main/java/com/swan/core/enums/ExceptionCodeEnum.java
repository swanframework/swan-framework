package com.swan.core.enums;

/**
 * @author zongf
 * @since 2023-04-16
 **/
public enum ExceptionCodeEnum {

    /** 未知系统异常 */
    UNKNOWN(-1, "未知系统异常"),

    VALIDATE(400, "请求参数非法"),

    MYBATIS(300, "数据库映射"),

    FREEMARKER(600, "模板"),
    LOCK(700, "加锁异常"),
    MINIO(800, "mino"),
    POI(900, "poi"),

    ;

    private Integer code;

    private String message;


    ExceptionCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

}
