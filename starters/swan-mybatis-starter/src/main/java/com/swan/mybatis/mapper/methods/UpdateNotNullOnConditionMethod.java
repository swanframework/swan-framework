package com.swan.mybatis.mapper.methods;


import java.util.concurrent.locks.Condition;

public interface UpdateNotNullOnConditionMethod<ID, E, C>  extends BaseMethod {

    public boolean updateNotNullOnCondition(E e, Condition condition); //智能处理 version

}
