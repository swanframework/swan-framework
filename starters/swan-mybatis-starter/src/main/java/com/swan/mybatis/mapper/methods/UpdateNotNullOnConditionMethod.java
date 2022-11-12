package com.swan.mybatis.mapper.methods;


import java.util.concurrent.locks.Condition;

public interface UpdateNotNullOnConditionMethod<ID, E>  extends BaseMethod {

    public boolean updateNotNullOnCondition(E eondition); //智能处理 version

}
