package com.swan.mybatis.mapper.methods;

public interface UpdateByIdMethod<ID, E>  extends BaseMethod {

    /** 按 id 更新记录，使用全部字段，可将数据库列更新为 null
     * @param entity id 不为空 的对象
     * @return 是否成功更新记录
     */
    public boolean updateById(E entity);

}
