package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.mapper.params.OrderBy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectAllMethod<ID, E>  extends BaseMethod {

    /** 查询全部记录
     * @param orderByList 排序规则
     * @return 无记录时，返回空的 list 而非 null
     */
    public List<E> selectAll(@Param("orderRules") OrderBy... orderByList);

}
