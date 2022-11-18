package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.SelectOption;
import org.apache.ibatis.annotations.Param;

public interface SelectMethod<ID, E>  extends BaseMethod {

    /** 根据条件查询一条记录，需要确定筛选条件下只有一条记录 <br/>
     * @param condition 筛选条件。传 null 时，会查询所有记录
     * @param options 查询选项，可指定排序方式、查询字段等。虽是可变变量，但最多只可传入一个参数
     * @return 无记录时，返回 null; 存在多条记录时, 抛出异常
     */
    public E selectOnCondition(@Param("condition") Condition condition,
                               @Param("options") SelectOption... options);

}
