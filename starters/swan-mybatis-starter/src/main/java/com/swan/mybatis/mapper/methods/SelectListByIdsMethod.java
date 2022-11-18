package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.OrderBy;
import com.swan.mybatis.condition.SelectOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectListByIdsMethod<ID, E>  extends BaseMethod {

    /** 根据主键列表查询多条记录
     * @param idList 主键里诶博爱
     * @param option 查询参数
     * @return 无记录时返回空的 list 而非 null
     */
    public List<E> selectListInIds(@Param("idList") List<ID> idList, @Param("option")SelectOption... option);

}
