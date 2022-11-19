package com.swan.mybatis.factory;

import com.swan.mybatis.anno.*;
import com.swan.mybatis.enums.IdGeneratorType;
import com.swan.mybatis.exception.BaseMapperException;
import com.swan.mybatis.field.meta.DeleteFieldMetaInfo;
import com.swan.mybatis.field.meta.EntityMetaInfo;
import com.swan.mybatis.field.meta.FieldMetaInfo;
import com.swan.mybatis.field.meta.VersionFieldMetaInfo;
import com.swan.mybatis.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zongf
 * @date 2021-01-09
 */
@Slf4j
public class EntityMetaInfoFactory {

    public static EntityMetaInfo createEntityMetaInfo(Class entityType, String conditionName, List<String> ignoreFields) {
        if (!entityType.isAnnotationPresent(Table.class)) {
            throw new BaseMapperException(entityType.getName() + " 未使用 @Table 指定映射的表名");
        }

        EntityMetaInfo entityMetaInfo = new EntityMetaInfo();

        entityMetaInfo.setClassName(entityType.getCanonicalName());
        entityMetaInfo.setConditionName(conditionName);

        Table table = (Table) entityType.getAnnotation(Table.class);
        entityMetaInfo.setTableName(table.name());

        List<Field> declaredFields = ReflectUtil.getAllDeclareFields(entityType);
        for (Field declaredField : declaredFields) {
            char c = declaredField.getName().charAt(0);

            // 非字母开头熟悉，直接忽略
            if (!(('a' <= c && 'z' >= c) || ('A' < c && 'Z' > c))) {
                log.warn("非法属性, 不予映射, 类名:{}, 字段名:{}", entityType.getCanonicalName(),declaredField.getName());
                continue;
            }
            // 自定义全局属性忽略
            if (ignoreFields != null && ignoreFields.contains(declaredField.getName())) {
                continue;
            }
            if (declaredField.isAnnotationPresent(Ignore.class)) {
                continue;
            }
            if (declaredField.isAnnotationPresent(Id.class)) {
                Id id = declaredField.getAnnotation(Id.class);
                if (IdGeneratorType.AUTO_INC.equals(id.generatorType())) {
                    entityMetaInfo.setAutoIncId(true);
                }
                entityMetaInfo.setIdField(createCommonFieldMeta(declaredField));
            } else if (declaredField.isAnnotationPresent(Delete.class)) {
                entityMetaInfo.setDeleteField(createDeleteFieldMeta(declaredField, declaredField.getAnnotation(Delete.class)));
            } else if (declaredField.isAnnotationPresent(Version.class)) {
                entityMetaInfo.setVersionField(createVersionFieldMeta(declaredField, declaredField.getAnnotation(Version.class)));
            } else {
                entityMetaInfo.getCommonFields().add(createCommonFieldMeta(declaredField));
            }
        }

        if (entityMetaInfo.getIdField() == null) {
            throw new BaseMapperException(entityType.getName() + " 未使用 @Id 指定主键列");
        }

        return entityMetaInfo;
    }

    public static DeleteFieldMetaInfo createDeleteFieldMeta(Field field, Delete delete) {
        DeleteFieldMetaInfo deleteFieldInfo = new DeleteFieldMetaInfo();
        deleteFieldInfo.setPropertyName(field.getName());
        deleteFieldInfo.setType(field.getType().getCanonicalName());
        deleteFieldInfo.setColumnName(hungarian(deleteFieldInfo.getPropertyName()));
        deleteFieldInfo.setYes(delete.yes());
        deleteFieldInfo.setNo(delete.no());
        return deleteFieldInfo;
    }

    public static VersionFieldMetaInfo createVersionFieldMeta(Field field, Version version) {
        VersionFieldMetaInfo fieldMetaInfo = new VersionFieldMetaInfo();
        fieldMetaInfo.setPropertyName(field.getName());
        fieldMetaInfo.setType(field.getType().getCanonicalName());
        fieldMetaInfo.setColumnName(hungarian(fieldMetaInfo.getPropertyName()));
        fieldMetaInfo.setVersionStart(version.start());
        return fieldMetaInfo;
    }

    public static FieldMetaInfo createCommonFieldMeta(Field field) {
        FieldMetaInfo fieldMetaInfo = new FieldMetaInfo();
        fieldMetaInfo.setPropertyName(field.getName());
        fieldMetaInfo.setType(field.getType().getCanonicalName());
        fieldMetaInfo.setColumnName(hungarian(fieldMetaInfo.getPropertyName()));
        return fieldMetaInfo;
    }

    public static String hungarian(String name){
        StringBuilder sb=new StringBuilder(name);
        int temp=0;//定位
        if (!name.contains("_")) {
            for(int i=0;i<name.length();i++){
                if(Character.isUpperCase(name.charAt(i))){
                    sb.insert(i+temp, "_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }
}
