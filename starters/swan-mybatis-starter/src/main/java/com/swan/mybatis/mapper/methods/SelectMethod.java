package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.SelectOption;
import org.apache.ibatis.annotations.Param;

public interface SelectMethod<ID, E>  extends BaseMethod {

    /** 根据条件查询一条记录，需要确定筛选条件下只有一条记录 <br/>
     * @param condition 排序规则
     * @return 无记录时，返回 null; 存在多条记录时, 抛出异常
     */
    public E selectOnCondition(@Param("condition") Condition condition, @Param("option")SelectOption options);

}
