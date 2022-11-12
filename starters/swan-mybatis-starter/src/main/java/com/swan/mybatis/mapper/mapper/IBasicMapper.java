package com.swan.mybatis.mapper.mapper;

import com.swan.mybatis.mapper.methods.*;

/** 通用Mapper，集成物理删除Api
 * @author zongf
 * @date 2021-02-26
 */
public interface IBasicMapper<ID, E, C> extends
        CountMethod<ID, E, C>,
        CountOnConditionMethod<ID, E, C>,

        InsertListMethod<ID, E, C>,
        InsertMethod<ID, E, C>,
        InsertNotNullMethod<ID, E, C>,

        UpdateByIdMethod<ID, E, C>,
        UpdateMethod<ID, E, C>,
        UpdateNotNullByIdMethod<ID, E, C>,
        UpdateNotNullMethod<ID, E, C>,

        SelectAllMethod<ID, E, C>,
        SelectByIdMethod<ID, E, C>,
        SelectListByIdsMethod<ID, E, C>,
        SelectListOnConditionMethod<ID, E, C>,

        DeleteInIdsMethod<ID, E, C>,
        DeleteByIdMethod<ID, E, C>,
        DeleteListMethod<ID, E, C>,
        DeleteOnConditionMethod<ID, E, C>,
        DeleteMethod<ID, E, C>
{

}
