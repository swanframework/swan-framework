package com.swan.mybatis.mapper.methods;

public interface RemoveMethod<ID, E, C>  extends BaseMethod {

    public boolean remove(E entity); //智能处理version

}
