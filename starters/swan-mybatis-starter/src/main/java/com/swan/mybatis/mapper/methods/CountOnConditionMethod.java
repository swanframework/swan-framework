package com.swan.mybatis.mapper.methods;

import com.sun.istack.internal.NotNull;
import org.apache.ibatis.annotations.Param;

public interface CountOnConditionMethod<ID, E, C>  extends BaseMethod {

    /** 根据条件获取记录总数 <br/>
     *  * 当 condition 为 null 时，统计表中全部数据 <br/>
     *  * 如果删除策略为物理删除，则总数为表中实际数量 <br/>
     *  * 如果删除策略为伪删除，则总数为使用 @Delete 修饰字段等于 @Delete.no 的记录总数 <br/>
     * @param condition 查询条件
     * @return
     */
    public Integer countOnCondition(@Param("condition") @NotNull C condition);

}
