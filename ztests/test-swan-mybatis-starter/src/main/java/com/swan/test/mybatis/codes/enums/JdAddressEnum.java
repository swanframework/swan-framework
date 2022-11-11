package com.swan.test.mybatis.codes.enums;
import java.util.Arrays;
import java.util.Optional;

/**地址
* @author 高峰
* @date 2021-03-03
*/
public enum JdAddressEnum{


    /** 查询一级地址 */
    ADDRESS_PROVINCE("/api/area/getProvince", "查询一级地址"),


    /** 查询二级地址 */
    ADDRESS_CITY("/api/area/getCity", "查询二级地址"),


    /** 查询三级地址 */
    ADDRESS_COUNTY("/api/area/getCounty", "查询三级地址"),


    /** 查询四级地址 */
    ADDRESS_TOWN("/api/area/getTown", "查询四级地址"),


    /** 验证地址有效性 */
    ADDRESS_CHECK_AREA("/api/area/checkArea", "验证地址有效性"),


    /** 地址详情转换京东地址编码 */
    ADDRESS_TRANSFORM("/api/area/getJDAddressFromAddress", "地址详情转换京东地址编码"),

    ;

    private String value;
    
    private String desc;
    
    JdAddressEnum(String value, String desc) {
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
    * @return JdAddressEnum value对应的枚举类型
    * @author zongf
    * @date 2021-03-03
    */
    public static JdAddressEnum getEnum(String value) {
        return Arrays.stream(JdAddressEnum.values())
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
        Optional<JdAddressEnum> optional = Arrays.stream(JdAddressEnum.values())
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
        Optional<JdAddressEnum> optional = Arrays.stream(JdAddressEnum.values())
            .filter(enm -> enm.desc.equals(desc))
            .findFirst();
        return optional.isPresent() ? optional.get().value : null;
    }

}
