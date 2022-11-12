package com.swan.mybatis.mapper.methods;

public interface InsertMethod<ID, E>  extends BaseMethod {

    public boolean insert(E entity);

}
