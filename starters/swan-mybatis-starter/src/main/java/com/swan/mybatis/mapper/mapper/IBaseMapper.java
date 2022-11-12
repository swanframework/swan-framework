package com.swan.mybatis.mapper.mapper;

import com.swan.mybatis.mapper.methods.*;

/** 通用 Mapper，集成逻辑删除Api
 * @author zongf
 * @date 2021-02-26
 */
public interface IBaseMapper<ID, E> extends


        InsertListMethod<ID, E>,
        InsertMethod<ID, E>,
        InsertNotNullMethod<ID, E>,

        DeleteMethod<ID, E>,
        DeleteListMethod<ID, E>,
        DeleteByIdMethod<ID, E>,
        DeleteByIdsMethod<ID, E>,
        DeleteOnConditionMethod<ID, E>,

        UpdateMethod<ID, E>,
        UpdateByIdMethod<ID, E>,
        UpdateFieldsByIdMethod<ID, E>,
        UpdateFieldsMethod<ID, E>,
        UpdateNotNullByIdMethod<ID, E>,
        UpdateNotNullMethod<ID, E>,
        UpdateOnConditionMethod<ID, E>,

        SelectByIdMethod<ID, E>,
        SelectOnConditionMethod<ID, E>,
        SelectListByIdsMethod<ID, E>,
        SelectListOnConditionMethod<ID, E>,
        SelectFieldsByIdMethod<ID, E>,
        SelectFieldsByIdsMethod<ID, E>,
        SelectFieldsOnConditionMethod<ID, E>,
        SelectAllMethod<ID, E>,

        CountMethod<ID, E>,
        CountOnConditionMethod<ID, E>
{
}
