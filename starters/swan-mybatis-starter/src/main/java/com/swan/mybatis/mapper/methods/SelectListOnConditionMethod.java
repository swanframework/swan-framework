package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import org.apache.ibatis.annotations.Param;
import com.swan.mybatis.mapper.params.OrderRule;

import java.util.List;

public interface SelectListOnConditionMethod<ID, E>  extends BaseMethod {

    public List<E> selectListOnCondition(@Param("condition") Condition condition, @Param("orderRules") OrderRule... orderRules);

}
