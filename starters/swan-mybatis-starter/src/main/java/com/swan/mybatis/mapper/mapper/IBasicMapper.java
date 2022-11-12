package com.swan.mybatis.mapper.mapper;

import com.swan.mybatis.mapper.methods.*;

/** 通用Mapper，集成物理删除Api
 * @author zongf
 * @date 2021-02-26
 */
public interface IBasicMapper<ID, E> extends
        CountMethod<ID, E>,
        CountOnConditionMethod<ID, E>,

        InsertListMethod<ID, E>,
        InsertMethod<ID, E>,
        InsertNotNullMethod<ID, E>,

        UpdateByIdMethod<ID, E>,
        UpdateMethod<ID, E>,
        UpdateNotNullByIdMethod<ID, E>,
        UpdateNotNullMethod<ID, E>,

        SelectAllMethod<ID, E>,
        SelectByIdMethod<ID, E>,
        SelectListByIdsMethod<ID, E>,
        SelectListOnConditionMethod<ID, E>,

        DeleteInIdsMethod<ID, E>,
        DeleteByIdMethod<ID, E>,
        DeleteListMethod<ID, E>,
        DeleteOnConditionMethod<ID, E>,
        DeleteMethod<ID, E>
{

}
