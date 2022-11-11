package com.swan.mybatis.mapper.methods;

public interface SelectByIdMethod<ID, E, C>  extends BaseMethod {

    public E selectById(ID id);

}
