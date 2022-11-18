package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.SelectOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectListMethod<ID, E>  extends BaseMethod {

    /** 根据查询条件查询多条记录
     * @param condition 不能为空
     * @param option 查询参数
     * @return 无记录时返回空的 list 而非 null
     */
    public List<E> selectList(@Param("condition") Condition condition, @Param("option")SelectOption... option);

}
