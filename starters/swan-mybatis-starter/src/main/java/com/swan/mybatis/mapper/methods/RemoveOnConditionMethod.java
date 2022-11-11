package com.swan.mybatis.mapper.methods;

import org.apache.ibatis.annotations.Param;

public interface RemoveOnConditionMethod<ID, E, C>  extends BaseMethod {
    public int removeOnCondition(@Param("condition") C condition);
}
