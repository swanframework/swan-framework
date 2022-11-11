package com.swan.test.mybatis.codes.enums;
import java.util.Arrays;
import java.util.Optional;

/**token
* @author 高峰
* @date 2021-03-03
*/
public enum JdTokenEnum{


    /** 获取Access Token */
    TOKEN_ACCESS("/oauth2/accessToken", "获取Access Token"),


    /** 刷新 Access Token */
    TOKEN_REFRESH("/oauth2/refreshToken", "刷新 Access Token"),

    ;

    private String value;
    
    private String desc;
    
    JdTokenEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    
    /** 获取枚举对象的value属性
    * @return String 枚举value
    * @author zongf
    * @date 2021-03-03
    */
    public String value(){
        return this.value;
    }
    
    /** 获取枚举对象的desc属性
    * @return String 枚举desc
    * @author zongf
    * @date 2021-03-03
    */
    public String desc(){
        return this.desc;
    }
    
    /** 获取value对应的枚举类型, 如果不存在则返回null
    * @param value 枚举value
    * @return JdTokenEnum value对应的枚举类型
    * @author zongf
    * @date 2021-03-03
    */
    public static JdTokenEnum getEnum(String value) {
        return Arrays.stream(JdTokenEnum.values())
            .filter(enm -> value == enm.value)
            .findFirst().orElse(null);
    }
    
    /** 获取value对应的desc. 如果value不存在，则返回null
    * @param value 枚举value
    * @return String 枚举desc 或 null
    * @author zongf
    * @date 2021-03-03
    */
    public static String getDesc(String value) {
        Optional<JdTokenEnum> optional = Arrays.stream(JdTokenEnum.values())
            .filter(enm -> value == enm.value)
            .findFirst();
        return optional.isPresent() ? optional.get().desc : null;
    }
    
    /** 获取value对应的value. 如果value不存在，则返回null
    * @param desc 枚举desc
    * @return String 枚举value 或 null
    * @author zongf
    * @date 2021-03-03
    */
    public static String getValue(String desc) {
        Optional<JdTokenEnum> optional = Arrays.stream(JdTokenEnum.values())
            .filter(enm -> enm.desc.equals(desc))
            .findFirst();
        return optional.isPresent() ? optional.get().value : null;
    }

}
