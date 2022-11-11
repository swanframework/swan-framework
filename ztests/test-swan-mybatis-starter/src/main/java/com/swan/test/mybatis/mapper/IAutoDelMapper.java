package com.swan.test.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.swan.test.mybatis.condition.AutoDelCondition;
import com.swan.test.mybatis.po.AutoDelEntity;

/**
 * @author zongf
 * @date 2021-01-07
 */
@Mapper
public interface IAutoDelMapper extends TestBasicMapper<Long, AutoDelEntity, AutoDelCondition> {

    void truncate();

}
