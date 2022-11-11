package com.swan.test.mybatis.codes.enums;
import java.util.Arrays;
import java.util.Optional;

/**地址
* @author 高峰
* @date 2021-03-03
*/
public enum JdUriEnum{


    /** 获取Access Token: 获取token，每个接口都需要用 */
    TOKEN_ACCESS("/oauth2/accessToken", "获取Access Token"),


    /** 刷新 Access Token: 刷新token，token 24小时会过期 */
    TOKEN_REFRESH("/oauth2/refreshToken", "刷新 Access Token"),


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


    /** 提交订单 */
    ORDER_SUBMIT("/api/order/submitOrder", "提交订单"),


    /** 反查订单 */
    ORDER_SEARCH_BY_EXID("/api/order/selectJdOrderIdByThirdOrder", "反查订单"),


    /** 确认预占库存订单 */
    ORDER_CONFIRM("/api/order/confirmOrder", "确认预占库存订单"),


    /** 取消未确认订单 */
    ORDER_CANCEL("/api/order/cancel", "取消未确认订单"),


    /** 查询订单详情 */
    ORDER_DETAIL("/api/order/selectJdOrder", "查询订单详情"),


    /** 查询配送信息 */
    ORDER_TRACK("/api/order/orderTrack", "查询配送信息"),


    /** 确认收货 */
    ORDER_CONFIRM_RECEIVED("/api/order/confirmReceived", "确认收货"),


    /** 更新采购单号 */
    ORDER_UPDATE_PO_NO("/api/order/saveOrUpdatePoNo", "更新采购单号"),


    /** 查询新建订单列表 */
    ORDER_CHECK_NEW_ORDER("/api/checkOrder/checkNewOrder", "查询新建订单列表"),


    /** 查询妥投订单列表 */
    ORDER_CHECK_OLOK_ORDER("/api/checkOrder/checkDlokOrder", "查询妥投订单列表"),


    /** 查询拒收订单列表 */
    ORDER_CHECK_REFUSE_ORDER("/api/checkOrder/checkRefuseOrder", "查询拒收订单列表"),


    /** 查询完成订单列表 */
    ORDER_CHECK_COMPLETE_ORDER("/api/checkOrder/checkCompleteOrder", "查询完成订单列表"),


    /** 查询配送预计送达时间 */
    ORDER_PROMISE_TIPS("/api/order/getPromiseTips", "查询配送预计送达时间"),


    /** 批量确认收货接口 */
    ORDER_CONFIRM_RECEIVED_BATCH("/api/order/batchConfirmReceived", "批量确认收货接口"),


    /** 查询订单列表接口 */
    ORDER_CHECK("/api/checkOrder/checkOrder", "查询订单列表接口"),


    /** 查询余额 */
    PRICE_BALANCE("/api/price/getUnionBalance", "查询余额"),


    /** 查询余额变动明细 */
    PRICE_BALANCE_DETAIL("/api/price/getBalanceDetail", "查询余额变动明细"),


    /** 查询可售后商品 */
    AFTER_SALE_AVAILABLE("/api/afterSale/getAvailableNumberComp", "查询可售后商品"),


    /** 查询商品售后类型 */
    AFTER_SALE_TYPE("/api/afterSale/getCustomerExpectComp", "查询商品售后类型"),


    /** 查询商品逆向配送 */
    AFTER_SALE_("/api/afterSale/getWareReturnJdComp", "查询商品逆向配送"),


    /** 申请售后 */
    AFTER_SALE_APPLY("/api/afterSale/createAfsApply", "申请售后"),


    /** 填写发运信息 */
    AFTER_SALE_UPDATE_SEND_SKU("/api/afterSale/updateSendSku", "填写发运信息"),


    /** 查询服务单概要 */
    AFTER_SALE_SERVICE_LIST("/api/afterSale/getServiceListPage", "查询服务单概要"),


    /** 查询服务单明细 */
    AFTER_SALE_SERVICE_CANCEL("/api/afterSale/getServiceDetailInfo", "查询服务单明细"),


    /** 取消服务单 */
    AFTER_SALE_CANCEL("/api/afterSale/auditCancel", "取消服务单"),


    /** 查询退款明细 */
    AFTER_SALE_REFUND_DETAIL("/api/afterSale/getOrderPayByOrderId", "查询退款明细"),


    /** 确认服务单 */
    AFTER_SALE_CONFRIM("/api/afterSale/confirmAfsOrder", "确认服务单"),


    /** 查询服务单列表 */
    AFTER_SALE_SERIVE_LIST("/api/afterSale/getAfsServiceListPage", "查询服务单列表"),


    /** 申请开票 */
    INVOICE_SUBMIT("/api/invoice/submit", "申请开票"),


    /** 查询发票第三方申请单号 */
    INVOICE_QUERY_THRIRD_NO("/api/invoice/queryThrApplyNo", "查询发票第三方申请单号"),


    /** 查询发票概要 */
    INVOICE_QUERY("/api/invoice/select", "查询发票概要"),


    /** 查询发票明细 */
    INVOICE_DETAIL("/api/invoice/queryInvoiceItem", "查询发票明细"),


    /** 查询电子发票明细 */
    INVOICE_LIST("/api/invoice/getInvoiceList", "查询电子发票明细"),


    /** 查询发票运单号 */
    INVOICE_WAY_BILL("/api/invoice/waybill", "查询发票运单号"),


    /** 查询发票物流信息 */
    INVOICE_DELIVERY_NO("/api/invoice/queryDeliveryNo", "查询发票物流信息"),


    /** 取消发票申请 */
    INVOICE_CANCEL_APPLY("/api/invoice/cancel", "取消发票申请"),


    /** 按发票号查询发票物流信息 */
    INVOICE_LOGISTICS("/api/invoice/queryLogisticsByInvoiceNumber", "按发票号查询发票物流信息"),


    /** 查询推送信息 */
    MESSAGE_GET("/api/message/get", "查询推送信息"),


    /** 删除推送信息 */
    MESSAGE_DELETE("/api/message/del", "删除推送信息"),

    ;

    private String value;
    
    private String desc;
    
    JdUriEnum(String value, String desc) {
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
    * @return JdUriEnum value对应的枚举类型
    * @author zongf
    * @date 2021-03-03
    */
    public static JdUriEnum getEnum(String value) {
        return Arrays.stream(JdUriEnum.values())
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
        Optional<JdUriEnum> optional = Arrays.stream(JdUriEnum.values())
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
        Optional<JdUriEnum> optional = Arrays.stream(JdUriEnum.values())
            .filter(enm -> enm.desc.equals(desc))
            .findFirst();
        return optional.isPresent() ? optional.get().value : null;
    }

}
