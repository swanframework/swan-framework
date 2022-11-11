package com.swan.mybatis.mapper.methods;

public interface InsertNotNullMethod<ID, E, C>  extends BaseMethod {

    public boolean insertNotNull(E entity);

}
