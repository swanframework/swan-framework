package com.swan.mybatis.factory;

import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.mybatis.anno.*;
import com.swan.mybatis.enums.IdGeneratorType;
import com.swan.mybatis.exception.SwanMybatisException;
import com.swan.mybatis.mapper.field.meta.DeleteFieldMetaInfo;
import com.swan.mybatis.mapper.field.meta.EntityMetaInfo;
import com.swan.mybatis.mapper.field.meta.FieldMetaInfo;
import com.swan.mybatis.mapper.field.meta.VersionFieldMetaInfo;
import com.swan.core.utils.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zongf
 * @since 2021-01-09
 */
@Slf4j
public class EntityMetaInfoFactory {

    public static EntityMetaInfo createEntityMetaInfo(Class entityType, List<String> ignoreFields) {
        // 未使用 @Table 注解修饰，则抛出异常
        if (!entityType.isAnnotationPresent(Table.class)) {
            throw new SwanMybatisException(ExceptionCodeEnum.MYBATIS.code(), entityType.getName() + " 未使用 @Table 指定映射的表名");
        }

        Table table = (Table) entityType.getAnnotation(Table.class);

        EntityMetaInfo entityMetaInfo = new EntityMetaInfo();
        entityMetaInfo.setClassName(entityType.getCanonicalName());
        entityMetaInfo.setTableName(table.name());

        // 处理字段
        List<Field> declaredFields = ReflectUtil.getAllDeclareFields(entityType);
        for (Field declaredField : declaredFields) {
            char c = declaredField.getName().charAt(0);

            // 非字母开头熟悉，直接忽略。如jacoco 会自动为类添加 $jacocoData 属性
            if (!(('a' <= c && 'z' >= c) || ('A' < c && 'Z' > c))) {
                log.warn("非法属性, 不予映射, 类名:{}, 字段名:{}", entityType.getCanonicalName(),declaredField.getName());
                continue;
            }
            // 自定义全局属性忽略
            if (ignoreFields != null && ignoreFields.contains(declaredField.getName())) {
                continue;
            }
            // 字段使用了 @Ignore 注解修饰，则忽略属性
            if (declaredField.isAnnotationPresent(Ignore.class)) {
                continue;
            }

            // 对 @Id、@Version、@Delete 三种字段特殊处理
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
                // 普通字段处理
                entityMetaInfo.getCommonFields().add(createCommonFieldMeta(declaredField));
            }
        }

        if (entityMetaInfo.getIdField() == null) {
            throw new SwanMybatisException(ExceptionCodeEnum.MYBATIS.code(), entityType.getName() + " 未使用 @Id 指定主键列");
        }

        return entityMetaInfo;
    }

    private static DeleteFieldMetaInfo createDeleteFieldMeta(Field field, Delete delete) {
        DeleteFieldMetaInfo deleteFieldInfo = new DeleteFieldMetaInfo();
        deleteFieldInfo.setPropertyName(field.getName());
        deleteFieldInfo.setType(field.getType().getCanonicalName());
        deleteFieldInfo.setColumnName(hungarian(deleteFieldInfo.getPropertyName()));
        deleteFieldInfo.setYes(delete.yes());
        deleteFieldInfo.setNo(delete.no());
        return deleteFieldInfo;
    }

    private static VersionFieldMetaInfo createVersionFieldMeta(Field field, Version version) {
        VersionFieldMetaInfo fieldMetaInfo = new VersionFieldMetaInfo();
        fieldMetaInfo.setPropertyName(field.getName());
        fieldMetaInfo.setType(field.getType().getCanonicalName());
        fieldMetaInfo.setColumnName(hungarian(fieldMetaInfo.getPropertyName()));
        fieldMetaInfo.setVersionStart(version.start());
        return fieldMetaInfo;
    }

    private static FieldMetaInfo createCommonFieldMeta(Field field) {
        FieldMetaInfo fieldMetaInfo = new FieldMetaInfo();
        fieldMetaInfo.setPropertyName(field.getName());
        fieldMetaInfo.setType(field.getType().getCanonicalName());
        fieldMetaInfo.setColumnName(hungarian(fieldMetaInfo.getPropertyName()));
        return fieldMetaInfo;
    }

    private static String hungarian(String name){
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
