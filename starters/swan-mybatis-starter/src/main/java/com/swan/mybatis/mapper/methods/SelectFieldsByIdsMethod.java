package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.mapper.params.OrderRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectFieldsByIdsMethod<ID, E, C>  extends BaseMethod {

    public List<E> SelectFieldsListByIds(@Param("idList") List<ID> idList, @Param("orderRules") OrderRule... orderRules);

}
