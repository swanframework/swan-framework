package com.swan.test.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.swan.mybatis.mapper.methods.SelectListByIdsMethod;
import com.swan.test.mybatis.po.AutoEntity;

/**
 * @author zongf
 * @since 2021-01-07
 */
@Mapper
public interface CustomMethodMapper extends SelectListByIdsMethod<Long, AutoEntity> {


}
