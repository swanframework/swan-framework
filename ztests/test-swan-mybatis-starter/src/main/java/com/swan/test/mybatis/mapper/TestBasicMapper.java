package com.swan.test.mybatis.mapper;

import com.swan.mybatis.mapper.mapper.IBaseMapper;
import com.swan.mybatis.mapper.mapper.IBasicMapper;

public interface TestBasicMapper<ID, E, C> extends

        IBaseMapper<ID, E, C>, IBasicMapper<ID, E, C> {
}
