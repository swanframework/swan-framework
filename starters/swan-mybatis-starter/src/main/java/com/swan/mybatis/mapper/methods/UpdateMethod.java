package com.swan.mybatis.mapper.methods;

public interface UpdateMethod<ID, E>  extends BaseMethod {

    /** 更新记录，使用实体的全部字段 <br/>
     *  版本管理: 如果存在 @Version 修饰字段 <br/>
     *    * 存在: 则按 id 和 version 更新数据 <br/>
     *    * 否则: 只按 id 进行更新数据，和 updateById 一样 <br/>
     * @param entity id 不为空 的对象
     * @return 是否成功更新记录
     */
    public boolean update(E entity);

}
