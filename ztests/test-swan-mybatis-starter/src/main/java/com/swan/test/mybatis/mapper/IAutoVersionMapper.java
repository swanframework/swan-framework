package com.swan.test.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.swan.test.mybatis.condition.AutoVersionCondition;
import com.swan.test.mybatis.po.AutoVersionEntity;

/**
 * @author zongf
 * @date 2021-01-07
 */
@Mapper
public interface IAutoVersionMapper extends TestBasicMapper<Long, AutoVersionEntity, AutoVersionCondition> {

    void truncate();

}
