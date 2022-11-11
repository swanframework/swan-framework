package com.swan.mybatis.mapper.methods;


public interface DeleteMethod<ID, E, C>  extends BaseMethod {

    public boolean delete(E entity);		//智能处理version
}
