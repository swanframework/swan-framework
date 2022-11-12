package com.swan.mybatis.mapper.methods;


import com.sun.istack.internal.NotNull;

public interface UpdateFieldsByIdMethod<ID, E>  extends BaseMethod {

    /** 按 id 更新记录的部分字段
     * @param entity id 不能为空
     * @param fieldNames java 熟悉名，而非数据库列表，会自动完成映射
     * @return 是否成功更新数据
     */
    public boolean updateFieldsById(@NotNull E entity, String... fieldNames); //智能处理 version

}
