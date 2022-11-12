package com.swan.mybatis.mapper.methods;

public interface UpdateNotNullByIdMethod<ID, E>  extends BaseMethod {

    /** 按 id 更新记录，使用非 null 的字段
     * @param entity id 不为空 的对象
     * @return 是否成功更新记录
     */
    public boolean updateNotNullById(E entity);

}
