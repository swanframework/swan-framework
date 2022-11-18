package com.swan.mybatis.enums;

import java.util.Arrays;
import java.util.Optional;

/** Id 生成策略
 * @author zongf
 * @date 2020-11-27
 */
public enum IdGeneratorType {

    DEFAULT(0, "不处理"),
    AUTO_INC(1, "数据库自增"),
    UUID(2, "uuid"),
    SNOWID(3, "雪花算法"),
    CUSTOMER(4, "自定义"),
    ;

    private Integer code;

    private String desc;

    IdGeneratorType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举对象的code属性
     *
     * @return int 枚举code
     * @author zongf
     * @date 2020-11-27
     */
    public int code() {
        return this.code;
    }

    /**
     * 获取枚举对象的desc属性
     *
     * @return String 枚举desc
     * @author zongf
     * @date 2020-11-27
     */
    public String desc() {
        return this.desc;
    }

    /**
     * 获取code对应的枚举类型, 如果不存在则返回null
     *
     * @param code 枚举code
     * @return IdType code对应的枚举类型
     * @author zongf
     * @date 2020-11-27
     */
    public static IdGeneratorType getEnumOfCode(int code) {
        return Arrays.stream(IdGeneratorType.values())
                .filter(enm -> code == enm.code)
                .findFirst().orElse(null);
    }

    /**
     * 获取desc对应的枚举类型, 如果不存在则返回null
     *
     * @param desc 枚举desc
     * @return IdType desc对应的枚举类型
     * @author zongf
     * @date 2020-11-27
     */
    public static IdGeneratorType getEnumOfDesc(String desc) {
        return Arrays.stream(IdGeneratorType.values())
                .filter(enm -> enm.desc.equals(desc))
                .findFirst().orElse(null);
    }

    /**
     * 获取code对应的desc. 如果code不存在，则返回null
     *
     * @param code 枚举code
     * @return String 枚举desc 或 null
     * @author zongf
     * @date 2020-11-27
     */
    public static String getDesc(int code) {
        Optional<IdGeneratorType> optional = Arrays.stream(IdGeneratorType.values())
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
     * @date 2020-11-27
     */
    public static Integer getCode(String desc) {
        Optional<IdGeneratorType> optional = Arrays.stream(IdGeneratorType.values())
                .filter(enm -> enm.desc.equals(desc))
                .findFirst();
        return optional.isPresent() ? optional.get().code : null;
    }

}
