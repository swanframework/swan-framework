package com.swan.mybatis.mapper.methods;


public interface DeleteByIdMethod<ID, E, C>  extends BaseMethod {

    public boolean deleteById(ID id);

}
