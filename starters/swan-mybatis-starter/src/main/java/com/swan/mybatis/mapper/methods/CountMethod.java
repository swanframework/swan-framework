package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import org.apache.ibatis.annotations.Param;

public interface CountMethod<ID, E>  extends BaseMethod {

    /** 统计表中未删除的记录总数 <br/>
     *  删除策略: 是否使用 @Delete 修饰字段 <br/>
     *    * 物理删除: 总数为表中实际数量 <br/>
     *    * 逻辑删除: 则总数为使用 @Delete 修饰字段等于 @Delete.no 的记录总数 <br/>
     * @param condition 为空时，统计全部数据
     * @return Integer 记录总数
     */
    public int count(@Param("condition")Condition condition);

}
