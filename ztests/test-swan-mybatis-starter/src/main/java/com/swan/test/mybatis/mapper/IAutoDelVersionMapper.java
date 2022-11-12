package com.swan.test.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.swan.test.mybatis.condition.AutoDelVersionCondition;
import com.swan.test.mybatis.po.AutoDelVersionEntity;

/**
 * @author zongf
 * @date 2021-01-07
 */
@Mapper
public interface IAutoDelVersionMapper extends TestBasicMapper<Long, AutoDelVersionEntity> {

    void truncate();

}
