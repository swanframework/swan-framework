package com.swan.mybatis.mapper.methods;

public interface UpdateNotNullByIdMethod<ID, E, C>  extends BaseMethod {

    public boolean updateNotNullById(E entity);

}
