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

    /** 降序排列
     * @param column 字段
     * @return OrderBy
     */
    public static OrderBy asc(String column) {
        return new OrderBy(column, "asc");
    }

    /** 升序排列
     * @param column 字段
     * @return OrderBy
     */
    public static OrderBy desc(String column) {
        return new OrderBy(column, "desc");
    }

}
