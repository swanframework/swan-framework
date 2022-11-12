package com.swan.test.mybatis.mapper;

import com.swan.mybatis.mapper.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.swan.test.mybatis.po.AutoVersionEntity;

/**
 * @author zongf
 * @date 2021-01-07
 */
@Mapper
public interface IAutoVersionMapper extends IBaseMapper<Long, AutoVersionEntity> {

    void truncate();

}
