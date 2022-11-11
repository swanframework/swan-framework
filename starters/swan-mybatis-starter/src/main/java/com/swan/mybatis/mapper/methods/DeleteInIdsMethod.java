package com.swan.mybatis.mapper.methods;

import java.util.List;

public interface DeleteInIdsMethod<ID, E, C>  extends BaseMethod {

    public int deleteInIds(List<ID> idList);
}
