package com.swan.mybatis.mapper.params;

/** 排序规则
 * @author zongf
 * @date 2021-01-09
 */
public class OrderRule {

    /** 排序字段 */
    private String column;

    /** 排序类型 */
    private String type;

    private OrderRule(String orderColumn, String orderType){
        this.column = orderColumn;
        this.type = orderType;
    }

    public static OrderRule asc(String orderColumn) {
        return new OrderRule(orderColumn, "asc");
    }

    public static OrderRule desc(String orderColumn) {
        return new OrderRule(orderColumn, "desc");
    }

}
