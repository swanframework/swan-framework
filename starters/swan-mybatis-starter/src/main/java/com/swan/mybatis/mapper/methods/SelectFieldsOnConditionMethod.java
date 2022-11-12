package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.mapper.params.OrderRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectFieldsOnConditionMethod<ID, E>  extends BaseMethod {

    public List<E> SelectFieldsListOnCondition(@Param("distinct") boolean distinct, @Param("condition") Condition condition, @Param("orderRules") OrderRule... orderRules);

}
