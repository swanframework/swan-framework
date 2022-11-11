package com.swan.mybatis.mapper.methods;

public interface RemoveByIdMethod<ID, E, C>  extends BaseMethod {

    public boolean removeById(ID id);

}
