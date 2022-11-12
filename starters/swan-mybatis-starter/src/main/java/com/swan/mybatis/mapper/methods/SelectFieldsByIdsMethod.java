package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.mapper.params.OrderBy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectFieldsByIdsMethod<ID, E>  extends BaseMethod {


    /**
     * @param idList
     * @param orderByList
     * @return
     */
    public List<E> selectFieldsListByIds(@Param("idList") List<ID> idList, @Param("orderRules") OrderBy... orderByList);

}
