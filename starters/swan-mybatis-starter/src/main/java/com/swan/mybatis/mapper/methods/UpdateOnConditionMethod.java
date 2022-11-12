package com.swan.mybatis.mapper.methods;


import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

public interface UpdateOnConditionMethod<ID, E, C>  extends BaseMethod {

    public boolean updateOnCondition(E e, Condition condition); //智能处理 version

}
