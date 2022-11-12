package com.swan.mybatis.mapper.methods;

public interface SelectByIdMethod<ID, E>  extends BaseMethod {

    public E selectById(ID id, String... fields);

}
