package com.swan.mybatis.mapper.methods;

import org.apache.ibatis.annotations.Param;
import com.swan.mybatis.mapper.params.OrderRule;

import java.util.List;

public interface SelectListInIdsMethod<ID, E, C>  extends BaseMethod {

    public List<E> selectListInIds(@Param("idList") List<ID> idList, @Param("orderRules") OrderRule... orderRules);

}
