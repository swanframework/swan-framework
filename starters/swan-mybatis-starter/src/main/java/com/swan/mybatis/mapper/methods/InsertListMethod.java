package com.swan.mybatis.mapper.methods;

import java.util.List;

public interface InsertListMethod<ID, E, C>  extends BaseMethod {

    public int insertList(List<E> entity);

}
