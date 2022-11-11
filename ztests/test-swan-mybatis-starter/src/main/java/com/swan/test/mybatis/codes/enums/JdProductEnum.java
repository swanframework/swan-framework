package com.swan.test.mybatis.codes.enums;
import java.util.Arrays;
import java.util.Optional;

/**商品
* @author 高峰
* @date 2021-03-03
*/
public enum JdProductEnum{


    /** 查询商品池编号 */
    PRODUCT_POOL_NUM("/api/product/getPageNum", "查询商品池编号"),


    /** 查询池内商品编号 */
    PRODUCT_ID_LIST("/api/product/getSkuByPage", "查询池内商品编号"),


    /** 查询商品详情 */
    PRODUCT_DETAIL("/api/product/getDetail", "查询商品详情"),


    /** 查询商品图片 */
    PRODUCT_SKU_IMAGES("/api/product/skuImage", "查询商品图片"),


    /** 查询商品上下架状态 */
    PRODUCT_SKU_STATE("/api/product/skuState", "查询商品上下架状态"),


    /** 验证商品可售性 */
    PRODUCT_CHECK_SALE("/api/product/check", "验证商品可售性"),


    /** 查询商品区域购买限制 */
    PRODUCT_CHECK_AREA_LIMIT("/api/product/checkAreaLimit", "查询商品区域购买限制"),


    /** 查询赠品信息 */
    PRODUCT_SKU_GIFT("/api/product/getSkuGift", "查询赠品信息"),


    /** 查询商品延保 */
    PRODUCT_SKU_YANBAO("/api/product/getYanbaoSku", "查询商品延保"),


    /** 验证货到付款 */
    PRODUCT_CHECK_COD("/api/product/getIsCod", "验证货到付款"),


    /** 批量验证货到付款 */
    PRODUCT_CHECK_COD_BATCH("/api/product/getBatchIsCod", "批量验证货到付款"),


    /** 搜索商品 */
    PRODUCT_SEARCH("/api/search/search", "搜索商品"),


    /** 查询同类商品 */
    PRODUCT_SEARCH_SIMILAR("/api/product/getSimilarSku", "查询同类商品"),


    /** 查询分类信息 */
    PRODUCT_SKU_CATEGORY("/api/product/getCategory", "查询分类信息"),


    /** 查询商品状态验证统一接口信息 */
    PRODUCT_TOTAL_CHECK("/api/product/totalCheck", "查询商品状态验证统一接口信息"),


    /** 商品权益接口 */
    PRODUCT_SKU_EXTRA_INFO("/api/product/ getSkuExtraInfo", "商品权益接口"),


    /** 查询商品售卖价 */
    PRODUCT_PRICE_SELL("/api/price/getSellPrice", "查询商品售卖价"),


    /** 查询商品库存 */
    PRODUCT_STOCK("/api/stock/getNewStockById", "查询商品库存"),


    /** 查询运费 */
    PRODUCT_FREIGHT("/api/order/getFreight", "查询运费"),


    /** 查询预约日历 */
    PRODUCT_PROMISE_CALENDAR("/api/order/promiseCalendar", "查询预约日历"),

    ;

    private String value;
    
    private String desc;
    
    JdProductEnum(String value, String desc) {
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
    * @return JdProductEnum value对应的枚举类型
    * @author zongf
    * @date 2021-03-03
    */
    public static JdProductEnum getEnum(String value) {
        return Arrays.stream(JdProductEnum.values())
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
        Optional<JdProductEnum> optional = Arrays.stream(JdProductEnum.values())
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
        Optional<JdProductEnum> optional = Arrays.stream(JdProductEnum.values())
            .filter(enm -> enm.desc.equals(desc))
            .findFirst();
        return optional.isPresent() ? optional.get().value : null;
    }

}
