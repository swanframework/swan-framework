package com.swan.mybatis.mapper.methods;

import java.util.List;

public interface RemoveInIdsMethod<ID, E, C>  extends BaseMethod {

    public int removeInIds(List<ID> idList);

}
