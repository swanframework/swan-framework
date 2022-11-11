package com.swan.test.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.swan.test.mybatis.condition.AutoCondition;
import com.swan.test.mybatis.po.AutoEntity;

import java.util.List;

/**
 * @author zongf
 * @date 2021-01-07
 */
@Mapper
public interface IAutoMapper extends TestBasicMapper<Long, AutoEntity, AutoCondition> {

    void truncate();

    List<AutoEntity> queryListByIds(List<Integer> ids);

}
