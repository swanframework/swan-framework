package com.swan.mybatis.mapper.methods;

public interface InsertNotNullMethod<ID, E>  extends BaseMethod {

    public boolean insertNotNull(E entity);

}
