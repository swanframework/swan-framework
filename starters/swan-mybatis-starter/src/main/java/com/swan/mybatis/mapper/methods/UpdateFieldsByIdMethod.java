package com.swan.mybatis.mapper.methods;


import java.util.List;

public interface UpdateFieldsByIdMethod<ID, E>  extends BaseMethod {

    public boolean UpdateFieldsById(List<String> fields, E entity); //智能处理 version

}
