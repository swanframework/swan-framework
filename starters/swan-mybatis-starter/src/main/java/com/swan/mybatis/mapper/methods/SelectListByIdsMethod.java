package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.SelectOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectListByIdsMethod<ID, E>  extends BaseMethod {

    /** 根据主键列表查询多条记录
     * @param idList 主键里诶博爱
     * @param options 查询选项，可指定排序方式、查询字段等。虽是可变变量，但最多只可传入一个参数
     * @return 无记录时返回空的 list 而非 null
     */
    public List<E> selectListByIds(@Param("idList") List<ID> idList, @Param("options")SelectOption... options);

}
