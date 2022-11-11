package com.swan.mybatis.mapper.methods;

import org.apache.ibatis.annotations.Param;

public interface DeleteOnConditionMethod<ID, E, C>  extends BaseMethod {

    public int deleteOnCondition(@Param("condition") C condition);

}
