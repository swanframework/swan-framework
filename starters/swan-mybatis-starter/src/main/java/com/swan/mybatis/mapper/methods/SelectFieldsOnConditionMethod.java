package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.mapper.params.OrderRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectFieldsOnConditionMethod<ID, E, C>  extends BaseMethod {

    public List<E> SelectFieldsListOnCondition(@Param("distinct") boolean distinct, @Param("condition") C condition, @Param("orderRules") OrderRule... orderRules);

}
