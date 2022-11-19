package com.swan.test.mybatis.mapper;

import com.swan.mybatis.mapper.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.swan.test.mybatis.po.AutoDelVersionEntity;

/**
 * @author zongf
 * @since 2021-01-07
 */
@Mapper
public interface IAutoDelVersionMapper extends IBaseMapper<Long, AutoDelVersionEntity> {

    void truncate();

    AutoDelVersionEntity locateById(Long id);
}
