package com.swan.mybatis.mapper.methods;

public interface SelectByIdMethod<ID, E>  extends BaseMethod {

    /** 通过id 查询一条记录。
     * @param id 主键 id
     * @param fields java 实体属性名列表，自动转换为数据库字段列表
     * @return 无记录时，返回 null
     */
    public E selectById(ID id, String... fields);

}
