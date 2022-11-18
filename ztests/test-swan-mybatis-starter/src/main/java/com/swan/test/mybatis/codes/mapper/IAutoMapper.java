 package com.swan.test.mybatis.codes.mapper;

import com.swan.mybatis.mapper.mapper.IBaseMapper;
import com.swan.test.mybatis.po.AutoEntity;

 /**
* @author zongf
* @date 2021-03-25
*/
public interface IAutoMapper extends IBaseMapper<Long, AutoEntity> {
    void truncate();
}
