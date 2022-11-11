package com.swan.mybatis.mapper.methods;


public interface UpdateNotNullMethod<ID, E, C>  extends BaseMethod {

    public boolean updateNotNull(E entity); //智能处理 version

}
