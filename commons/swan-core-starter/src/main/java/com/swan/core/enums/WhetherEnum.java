package com.swan.core.enums;

import java.util.Arrays;

/** 是否枚举
 * @author zongf
 * @date 2021-10-28
 */
public enum WhetherEnum {

    YES(1, "是"),
    NO(0,"否");

    private Integer code;

    private String desc;

    WhetherEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

    /** 获取 code 对应的枚举, 不存在时, 返回null
     * @param code
     * @return WhetherEnum
     * @author zongf
     * @date 2021-10-28
     */
    public static WhetherEnum getEnumByCode(Integer code){
        return code == null ? null : Arrays.stream(WhetherEnum.values())
                .filter(enm -> code.equals(enm.code))
                .findFirst()
                .orElse(null);
    }

    /** 获取 desc 对应的 枚举, 不存在时返回 null
     * @param desc
     * @return WhetherEnum
     * @author zongf
     * @date 2021-10-28
     */
    public static WhetherEnum getEnumByDesc(String desc){
        return desc == null ? null : Arrays.stream(WhetherEnum.values())
                .filter(enm -> desc.equals(enm.desc))
                .findFirst()
                .orElse(null);
    }

    /** 获取 code 对应的 desc, 不存在时返回 null
     * @param code
     * @return WhetherEnum
     * @author zongf
     * @date 2021-10-28
     */
    public static String getDesc(Integer code) {
        WhetherEnum enm = getEnumByCode(code);
        return enm == null ? null : enm.desc;
    }

    /** 获取 desc 对应的 code, 不存在时返回 null
     * @param desc
     * @return WhetherEnum
     * @author zongf
     * @date 2021-10-28
     */
    public static Integer getCode(String desc) {
        WhetherEnum enm = getEnumByDesc(desc);
        return enm == null ? null : enm.code;
    }

}
