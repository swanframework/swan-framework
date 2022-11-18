package com.swan.mybatis.condition;

/** 排序规则
 * @author zongf
 * @since 2021-01-09
 */
public class OrderBy {

    /** 排序字段 */
    private String column;

    /** 排序类型 */
    private String type;

    private OrderBy(String orderColumn, String orderType){
        this.column = orderColumn;
        this.type = orderType;
    }

    public static OrderBy asc(String orderColumn) {
        return new OrderBy(orderColumn, "asc");
    }

    public static OrderBy desc(String orderColumn) {
        return new OrderBy(orderColumn, "desc");
    }

}
