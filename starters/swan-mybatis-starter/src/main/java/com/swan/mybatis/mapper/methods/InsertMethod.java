package com.swan.mybatis.mapper.methods;

public interface InsertMethod<ID, E, C>  extends BaseMethod {

    public boolean insert(E entity);

}
