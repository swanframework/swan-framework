package com.swan.mybatis.mapper.methods;


public interface DeleteByIdMethod<ID, E>  extends BaseMethod {

    public boolean deleteById(ID id);

}
