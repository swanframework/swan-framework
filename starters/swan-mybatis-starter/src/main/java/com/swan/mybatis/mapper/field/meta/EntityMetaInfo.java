package com.swan.mybatis.mapper.field.meta;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/** 实体信息
 * @author zongf
 * @date 2021-01-09
 */
@Setter @Getter @ToString
public class EntityMetaInfo {

    // 表名称
    private String tableName;

    // 类全限定名称
    private String className;

    // 普通字段列表
    private List<FieldMetaInfo> commonFields = new ArrayList<>();

    // id 字段
    private FieldMetaInfo idField;

    // 版本号字段名
    private VersionFieldMetaInfo versionField;

    // 删除字段名
    private DeleteFieldMetaInfo deleteField;

    // 是否数据库自增
    private boolean isAutoIncId;

    // 单表检索条件类型
    private String conditionName;

}
