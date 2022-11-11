package com.swan.mybatis.mapper.methods;

public interface UpdateMethod<ID, E, C>  extends BaseMethod {

    public boolean update(E entity);	//智能处理 version

}
