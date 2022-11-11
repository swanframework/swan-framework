package com.swan.mybatis.mapper.mapper;

import com.swan.mybatis.mapper.methods.*;

/** 通用 Mapper，集成逻辑删除Api
 * @author zongf
 * @date 2021-02-26
 */
public interface IBaseMapper<ID, E, C> extends
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
        SelectListInIdsMethod<ID, E, C>,
        SelectListOnConditionMethod<ID, E, C>,

        RemoveByIdMethod<ID, E, C>,
        RemoveInIdsMethod<ID, E, C>,
        RemoveListMethod<ID, E, C>,
        RemoveMethod<ID, E, C>,
        RemoveOnConditionMethod<ID, E, C>
{
}
