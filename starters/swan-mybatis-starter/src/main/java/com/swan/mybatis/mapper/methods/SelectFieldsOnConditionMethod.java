package com.swan.mybatis.mapper.methods;

import com.sun.istack.internal.NotNull;
import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.mapper.params.OrderBy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectFieldsOnConditionMethod<ID, E>  extends BaseMethod {

    /** 查询多条记录，只查询部分字段
     * @param distinct 是否去重
     * @param fieldNames java 实体属性名列表，非数据库字段列表，会自动完成映射
     * @param condition 筛选条件
     * @param orderByList 排序规则
     * @return 无记录时返回空的 list, 而非 Null
     */
    public List<E> selectFieldsListOnCondition(@Param("distinct") boolean distinct, @Param("fieldNames") List<String> fieldNames,
                                               @NotNull @Param("condition") Condition condition,
                                               @Param("orderRules") OrderBy... orderByList);

}
