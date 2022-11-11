package com.swan.mybatis.mapper.methods;

import java.util.List;

public interface RemoveListMethod<ID, E, C>  extends BaseMethod {

    public int removeList(List<E> entity); //智能处理version

}
