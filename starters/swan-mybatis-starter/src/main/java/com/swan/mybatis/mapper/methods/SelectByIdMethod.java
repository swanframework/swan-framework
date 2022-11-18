package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.SelectOption;
import org.apache.ibatis.annotations.Param;

public interface SelectByIdMethod<ID, E>  extends BaseMethod {

    /** 通过id 查询一条记录。
     * @param id 主键 id
     * @param options 查询选项，可指定排序方式、查询字段等。虽是可变变量，但最多只可传入一个参数
     * @return 无记录时，返回 null
     */
    public E selectById(@Param("id") ID id,
                        @Param("options") SelectOption... options);

}
