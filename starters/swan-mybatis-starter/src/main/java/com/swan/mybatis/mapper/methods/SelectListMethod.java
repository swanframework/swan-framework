package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.SelectOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectListMethod<ID, E>  extends BaseMethod {

    /** 根据条件查询多条记录
     * @param condition 筛选条件。传 null 时，会查询所有记录
     * @param options 查询选项，可指定排序方式、查询字段等。虽是可变变量，但最多只可传入一个参数
     * @return 无记录时返回空的 list 而非 null
     */
    public List<E> selectList(@Param("condition") Condition condition,
                              @Param("options")SelectOption... options);

}
