package com.swan.mybatis.mapper.methods;

import com.sun.istack.internal.NotNull;

public interface UpdateFieldsMethod<ID, E>  extends BaseMethod {

    /** 更新记录的部分字段 <br/>
     *  版本管理:   <br/>
     *    * 存在时，按 id 和 version 进行更新 <br/>
     *    * 不存在时, 按 id 进行更新  <br/>
     * @param entity id 不能为空
     * @param fieldNames java 熟悉名，而非数据库列表，会自动完成映射
     * @return 是否成功更新数据
     */
    public boolean updateFields(@NotNull E entity, String... fieldNames); //智能处理 version

}
