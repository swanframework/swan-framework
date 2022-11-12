package com.swan.mybatis.mapper.methods;

public interface CountMethod<ID, E>  extends BaseMethod {

    /** 统计表中未删除的记录总数 <br/>
     *  * 物理删除: 总数为表中实际数量 <br/>
     *  * 逻辑删除: 则总数为使用 @Delete 修饰字段等于 @Delete.no 的记录总数 <br/>
     * @return Integer
     */
    public Integer count();

}
