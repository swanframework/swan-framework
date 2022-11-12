package com.swan.mybatis.mapper.methods;

public interface CountMethod<ID, E, C>  extends BaseMethod {

    /** 统计表中未删除的记录总数 <br/>
     *  * 如果删除策略为物理删除，则总数为表中实际数量 <br/>
     *  * 如果删除策略为伪删除，则总数为使用 @Delete 修饰字段等于 @Delete.no 的记录总数 <br/>
     * @return Integer
     */
    public Integer count();

}
