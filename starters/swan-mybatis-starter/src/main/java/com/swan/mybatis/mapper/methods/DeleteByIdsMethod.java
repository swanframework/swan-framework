package com.swan.mybatis.mapper.methods;

import com.sun.istack.internal.NotNull;

import java.util.Collection;

public interface DeleteByIdsMethod<ID, E>  extends BaseMethod {

    /** 根据 id 批量删除对象 <br/>
     *  删除策略: 是否使用 @Delete 修饰字段 <br/>
     *    * 逻辑删除: 执行 update 操作, 按 id 更新数据 <br/>
     *    * 物理删除: 执行 delete 操作, 按 id 删除数据 <br/>
     *  版本管理: 只按 id 进行操作
     * @param idList 主键id 列表，不为空
     * @return 删除的数据总数
     */
    public int deleteByIds(@NotNull Collection<ID> idList);

}
