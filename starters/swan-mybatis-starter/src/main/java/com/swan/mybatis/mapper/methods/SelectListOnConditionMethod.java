package com.swan.mybatis.mapper.methods;

import com.sun.istack.internal.NotNull;
import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.mapper.params.OrderBy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectListOnConditionMethod<ID, E>  extends BaseMethod {

    /** 根据查询条件查询多条记录
     * @param condition 不能为空
     * @param orderByList 排序规则
     * @return 无记录时返回空的 list 而非 null
     */
    public List<E> selectListOnCondition(@NotNull @Param("condition") Condition condition, @Param("orderRules") OrderBy... orderByList);

}
