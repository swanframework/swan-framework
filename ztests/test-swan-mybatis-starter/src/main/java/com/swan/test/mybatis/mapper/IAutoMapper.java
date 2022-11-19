package com.swan.test.mybatis.mapper;

import com.swan.mybatis.mapper.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.swan.test.mybatis.po.AutoEntity;

import java.util.List;

/**
 * @author zongf
 * @since 2021-01-07
 */
@Mapper
public interface IAutoMapper extends IBaseMapper<Long, AutoEntity> {

    void truncate();

    List<AutoEntity> queryListByIds(List<Integer> ids);

}
