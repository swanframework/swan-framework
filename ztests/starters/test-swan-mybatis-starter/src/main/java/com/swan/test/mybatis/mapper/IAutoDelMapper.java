package com.swan.test.mybatis.mapper;

import com.swan.mybatis.mapper.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.swan.test.mybatis.po.AutoDelEntity;

/**
 * @author zongf
 * @since 2021-01-07
 */
@Mapper
public interface IAutoDelMapper extends IBaseMapper<Long, AutoDelEntity> {

    void truncate();

    AutoDelEntity locateById(Long id);

}
