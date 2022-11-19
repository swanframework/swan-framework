package com.swan.mybatis.condition;

import com.swan.core.utils.NameUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/** 查询选择
 * @author zongf
 * @since 2022-11-18
 **/
@Getter
public class SelectOption {

    // 是否去重
    private boolean distinct;

    // 要查询的属性列表
    private List<String> fields;

    // 查询的列名
    private List<String> columns;

    // 排序规则
    private List<OrderBy> orderBys;

    // 物理分页偏移量
    private Integer offset;

    // 最多查询数量
    private Integer limit;

    private SelectOption() {
        this.fields = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.orderBys = new ArrayList<>();
    }

    public static SelectOption newInstance() {
        return new SelectOption();
    }

    /** 去重
     * @return SelectOption
     * @since 2022-11-18
     */
    public SelectOption distinct() {
        this.distinct = true;
        return this;
    }

    /** 是否去重
     * @param distinct 去重
     * @return SelectOption
     * @since 2022-11-18
     */
    public SelectOption distinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    /** 添加查询字段
     * @param fields java 属性列表
     * @return SelectOption
     * @since 2022-11-18
     */
    public SelectOption fields(String... fields) {
        for (String field : fields) {
            this.fields.add(field);
            this.columns.add(NameUtil.toHungaryName(field));
        }
        return this;
    }

    /** 添加降序字段
     * @param fields java 属性列表
     * @return SelectOption
     * @since 2022-11-18
     */
    public SelectOption asc(String... fields) {
        for (String field : fields) {
            this.orderBys.add(OrderBy.asc(NameUtil.toHungaryName(field)));
        }
        return this;
    }

    /** 添加升序字段
     * @param fields java 属性列表
     * @return SelectOption
     * @since 2022-11-18
     */
    public SelectOption desc(String... fields) {
        for (String field : fields) {
            this.orderBys.add(OrderBy.desc(NameUtil.toHungaryName(field)));
        }
        return this;
    }

    /** 设置分页信息
     * @param offset 起始偏移量
     * @param limit 查询数量
     * @return SelectOption
     * @since 2022-11-18
     */
    public SelectOption limit(Integer offset, Integer limit) {
        this.limit = limit;
        this.offset = offset;
        return this;
    }

}
