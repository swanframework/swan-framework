package com.swan.mybatis.mapper.methods;


public interface UpdateNotNullMethod<ID, E>  extends BaseMethod {

    public boolean updateNotNull(E entity); //智能处理 version

}
