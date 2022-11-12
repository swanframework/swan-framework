package com.swan.mybatis.mapper.methods;

import com.sun.istack.internal.NotNull;
import org.apache.ibatis.annotations.Param;

public interface CountOnConditionMethod<ID, E, C>  extends BaseMethod {

    /** 统计表中未删除的记录总数 <br/>
     *  * 物理删除: 总数为表中实际数量 <br/>
     *  * 逻辑删除: 则总数为使用 @Delete 修饰字段等于 @Delete.no 的记录总数 <br/>
     * @param condition
     * @return Integer
     */
    public Integer countOnCondition(@Param("condition") @NotNull C condition);

}
