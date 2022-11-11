package com.swan.mybatis.mapper.methods;

import org.apache.ibatis.annotations.Param;
import com.swan.mybatis.mapper.params.OrderRule;

import java.util.List;

public interface SelectListOnConditionMethod<ID, E, C>  extends BaseMethod {

    public List<E> selectListOnCondition(@Param("condition") C condition, @Param("orderRules") OrderRule... orderRules);

}
