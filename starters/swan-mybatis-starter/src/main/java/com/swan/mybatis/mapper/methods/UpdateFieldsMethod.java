package com.swan.mybatis.mapper.methods;


import java.util.List;

public interface UpdateFieldsMethod<ID, E>  extends BaseMethod {

    public boolean updateFields(List<String> fields, E entity); //智能处理 version

}
