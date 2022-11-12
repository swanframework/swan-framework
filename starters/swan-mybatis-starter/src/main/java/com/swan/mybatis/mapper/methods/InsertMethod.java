package com.swan.mybatis.mapper.methods;

public interface InsertMethod<ID, E>  extends BaseMethod {

    /** 插入记录，使用全部字段
     * @param entity 自动回写主键 id
     * @return 是否保存成功
     */
    public boolean insert(E entity);

}
