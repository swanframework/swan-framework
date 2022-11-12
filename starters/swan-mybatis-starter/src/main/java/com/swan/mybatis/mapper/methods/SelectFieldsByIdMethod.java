package com.swan.mybatis.mapper.methods;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectFieldsByIdMethod<ID, E>  extends BaseMethod {

    /** 通过 id 查询记录的部分字段
     * @param id 主键 id
     * @param fieldNames java 实体属性名列表，而非数据库字段名，会自动转换为字段名
     * @return E 无记录时，返回 null
     */
    public E selectFieldsById(@Param("idList") ID id, @Param("fieldNames") List<String> fieldNames);

}
