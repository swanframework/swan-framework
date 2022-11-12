package com.swan.mybatis.mapper.methods;

public interface InsertNotNullMethod<ID, E>  extends BaseMethod {

    /** 插入记录，只使用非 null 属性进行插入
     * @param entity 自动回写主键 id
     * @return 是否插入成功
     */
    public boolean insertNotNull(E entity);

}
