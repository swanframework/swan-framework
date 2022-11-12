package com.swan.mybatis.mapper.methods;

public interface UpdateByIdMethod<ID, E>  extends BaseMethod {

    public boolean updateById(E entity);

}
