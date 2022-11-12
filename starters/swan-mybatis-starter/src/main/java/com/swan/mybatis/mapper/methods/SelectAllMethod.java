package com.swan.mybatis.mapper.methods;

import org.apache.ibatis.annotations.Param;
import com.swan.mybatis.mapper.params.OrderRule;

import java.util.List;

public interface SelectAllMethod<ID, E>  extends BaseMethod {

    public List<E> selectAll(@Param("orderRules") OrderRule... orderRules);

}
