package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import org.apache.ibatis.annotations.Param;

public interface DeleteOnConditionMethod<ID, E>  extends BaseMethod {

    public int deleteOnCondition(@Param("condition") Condition condition);

}
