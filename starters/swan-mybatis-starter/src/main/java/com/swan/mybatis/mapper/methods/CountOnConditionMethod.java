package com.swan.mybatis.mapper.methods;

import org.apache.ibatis.annotations.Param;

public interface CountOnConditionMethod<ID, E, C>  extends BaseMethod {

    public Integer countOnCondition(@Param("condition") C condition);

}
