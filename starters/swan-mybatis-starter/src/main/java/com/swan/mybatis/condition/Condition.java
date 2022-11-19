package com.swan.mybatis.condition;

import com.swan.core.utils.NameUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/** where 条件类
 * @author zongf
 * @since 2022-11-12
 **/
@Getter
public class Condition {

    // 筛选条件
    private List<Criterion> criterionList;

    private Condition(List<Criterion> list) {
        this.criterionList = list;
    }

    public static Condition newInstance() {
        return new Condition(new ArrayList<>());
    }

    /** 添加 and 条件
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符
     * @param values 参数列表
     * @return Condition
     * @since 2022-11-18
     */
    public Condition and(String field, OpType opType, Object... values) {
        this.criterionList.add(CriterionFactory.create("and", NameUtil.toHungaryName(field), opType, values));
        return this;
    }

    /** 添加 or 条件
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符
     * @param values 参数列表
     * @return Condition
     * @since 2022-11-18
     */
    public Condition or(String field, OpType opType, Object... values) {
        this.criterionList.add(CriterionFactory.create("or", NameUtil.toHungaryName(field), opType, values));
        return this;
    }

    /** 清空所有筛选条件
     * @return Condition
     * @since 2022-11-18
     */
    public Condition clear() {
        criterionList.clear();
        return this;
    }


}
