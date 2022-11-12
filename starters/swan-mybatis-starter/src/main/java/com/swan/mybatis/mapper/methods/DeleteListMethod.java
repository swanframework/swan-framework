package com.swan.mybatis.mapper.methods;

import java.util.Collection;

public interface DeleteListMethod<ID, E>  extends BaseMethod {

    /** 批量删除对象 <br/>
     *  删除策略: 是否使用 @Delete 修饰字段 <br/>
     *    * 逻辑删除: 执行 update 操作, 按 id 更新数据 <br/>
     *    * 物理删除: 执行 delete 操作, 按 id 删除数据 <br/>
     *  版本管理: 如果存在 @Version 修饰字段 <br/>
     *    * 逻辑删除: 执行 update 操作，按 id 和 version 更新数据 <br/>
     *    * 物理删除: 执行 delete 操作, 按 id 和 version 删除数据 <br/>
     * @param entities id 不为空 的对象列表
     * @return 是否成功删除一条数据
     */
    public int deleteList(Collection<E> entities);

}
