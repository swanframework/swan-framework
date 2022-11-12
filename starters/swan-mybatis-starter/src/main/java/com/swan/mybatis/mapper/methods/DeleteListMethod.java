package com.swan.mybatis.mapper.methods;

import java.util.List;

public interface DeleteListMethod<ID, E>  extends BaseMethod {

    public int deleteList(List<E> entityList);	//智能处理version

}
