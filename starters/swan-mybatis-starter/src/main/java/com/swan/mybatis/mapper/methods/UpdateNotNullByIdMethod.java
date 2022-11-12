package com.swan.mybatis.mapper.methods;

public interface UpdateNotNullByIdMethod<ID, E>  extends BaseMethod {

    public boolean updateNotNullById(E entity);

}
