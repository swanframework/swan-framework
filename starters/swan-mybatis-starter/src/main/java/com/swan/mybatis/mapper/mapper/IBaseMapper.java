package com.swan.mybatis.mapper.mapper;

import com.swan.mybatis.mapper.methods.*;

/** 通用 Mapper，集成逻辑删除Api
 * @author zongf
 * @since 2021-02-26
 */
public interface IBaseMapper<ID, E> extends

        InsertListMethod<ID, E>,
        InsertMethod<ID, E>,
        InsertNotNullMethod<ID, E>,

        DeleteMethod<ID, E>,
        DeleteByIdMethod<ID, E>,
        DeleteByIdsMethod<ID, E>,

        UpdateMethod<ID, E>,
        UpdateByIdMethod<ID, E>,
        UpdateNotNullByIdMethod<ID, E>,
        UpdateNotNullMethod<ID, E>,

        SelectByIdMethod<ID, E>,
        SelectMethod<ID, E>,
        SelectListByIdsMethod<ID, E>,
        SelectListMethod<ID, E>,

        CountMethod<ID, E>
{
}
