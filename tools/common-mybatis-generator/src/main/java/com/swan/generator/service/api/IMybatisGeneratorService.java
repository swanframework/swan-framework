package com.swan.generator.service.api;


import com.swan.generator.enums.CommentTypeEnum;
import com.swan.generator.vo.EntityMeta;

public interface IMybatisGeneratorService {

    public EntityMeta generateEntity(String schemaName, String tableName, CommentTypeEnum commentTypeEnum);

    public String generateCondition(String schemaName, String tableName);

    public String generateMapper(String schemaName, String tableName);

    public String generateMapperXml(String schemaName, String tableName);

}
