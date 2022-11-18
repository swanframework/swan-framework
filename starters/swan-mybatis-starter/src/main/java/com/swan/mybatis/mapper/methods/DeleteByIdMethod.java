package com.swan.mybatis.mapper.methods;

public interface DeleteByIdMethod<ID, E>  extends BaseMethod {

    /** 根据 id 删除对象 <br/>
     *  删除策略: 是否使用 @Delete 修饰字段 <br/>
     *    * 逻辑删除: 执行 update 操作, 按 id 更新数据 <br/>
     *    * 物理删除: 执行 delete 操作, 按 id 删除数据 <br/>
     *  版本管理: 只按 id 进行操作
     * @param id id 不为空 的对象
     * @return 是否成功删除一条数据
     */
    public boolean deleteById(ID id);

}
