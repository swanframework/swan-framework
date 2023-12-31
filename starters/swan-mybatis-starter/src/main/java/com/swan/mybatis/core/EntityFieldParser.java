package com.swan.mybatis.core;

import com.swan.mybatis.anno.Encrypt;
import com.swan.mybatis.mapper.field.handler.*;
import org.apache.ibatis.mapping.SqlCommandType;
import com.swan.mybatis.anno.Id;
import com.swan.mybatis.anno.AutoTime;
import com.swan.mybatis.anno.ForceNull;
import com.swan.mybatis.enums.IdGeneratorType;
import com.swan.core.utils.ReflectUtil;

import java.lang.reflect.Field;
import java.util.*;

/** 字段解析器
 * @author zongf
 * @since 2021-01-08
 */
public class EntityFieldParser {

    /** 缓存需要处理的字段类型 */
    public static final Map<Class, Map<SqlCommandType, List<AbsFieldHandler>>> CACHE_MAP = new HashMap<>();

    /** 解析需要动态修改的字段
     * @param classes
     * @author zongf
     * @since 2021-01-08
     */
    public final void parseFields(Set<Class> classes) {

        for (Class aClass : classes) {

            Map<SqlCommandType, List<AbsFieldHandler>> clzMap = CACHE_MAP.computeIfAbsent(aClass, k -> new LinkedHashMap<>());

            for (Field declaredField : ReflectUtil.getAllDeclareFields(aClass)) {
                // 采用if-else 结构依次解析 @Id, @AutoTime 等注解, 保证一个字段只有一个注解生效
                if (declaredField.isAnnotationPresent(Id.class)) {
                    parseId(clzMap, declaredField, declaredField.getAnnotation(Id.class));
                } else if (declaredField.isAnnotationPresent(AutoTime.class)) {
                    parseAutoTime(clzMap, declaredField, declaredField.getAnnotation(AutoTime.class));
                } else if (declaredField.isAnnotationPresent(ForceNull.class)) {
                    parseForceNull(clzMap, declaredField, declaredField.getAnnotation(ForceNull.class));
                } else if (declaredField.isAnnotationPresent(Encrypt.class)) {
                    parseEncrypt(clzMap, declaredField, declaredField.getAnnotation(Encrypt.class));

                }
            }
        }
    }



    // 处理 @Id 注解
    private void parseId(Map<SqlCommandType, List<AbsFieldHandler>> clzMap, Field declaredField, Id id) {
        // 对于默认值情况不处理
        if (!IdGeneratorType.DEFAULT.equals(id.generatorType())) {
            List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.INSERT, k -> new ArrayList<>());
            fieldMap.add(new IdFieldHandler(declaredField, id.generatorType(), id.generator()));
        }
    }

    // 处理 @AutoTime 注解
    private void parseAutoTime(Map<SqlCommandType, List<AbsFieldHandler>> clzMap, Field declaredField, AutoTime autoTime) {
        if (autoTime != null) {
            if (autoTime.onCreate()) {
                List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.INSERT, k -> new ArrayList<>());
                fieldMap.add(new AutoTimeFieldHandler(declaredField));
            }
            if (autoTime.onUpdate()) {
                List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.UPDATE, k -> new ArrayList<>());
                fieldMap.add(new AutoTimeFieldHandler(declaredField));
            }
            if (autoTime.onDelete()) {
                List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.DELETE, k -> new ArrayList<>());
                fieldMap.add(new AutoTimeFieldHandler(declaredField));
            }
        }
    }

    // 处理 @ForceNull 注解
    private void parseForceNull(Map<SqlCommandType, List<AbsFieldHandler>> clzMap, Field declaredField, ForceNull autoTime) {
        if (autoTime != null) {
            if (autoTime.onCreate()) {
                List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.INSERT, k -> new ArrayList<>());
                fieldMap.add(new ForceNullFieldHandler(declaredField));
            }
            if (autoTime.onUpdate()) {
                List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.UPDATE, k -> new ArrayList<>());
                fieldMap.add(new ForceNullFieldHandler(declaredField));
            }
            if (autoTime.onDelete()) {
                List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.DELETE, k -> new ArrayList<>());
                fieldMap.add(new ForceNullFieldHandler(declaredField));
            }
        }
    }

    private void parseEncrypt(Map<SqlCommandType, List<AbsFieldHandler>> clzMap, Field declaredField, Encrypt encrypt) {
        if (encrypt != null) {
            if (encrypt.onCreate()) {
                List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.INSERT, k -> new ArrayList<>());
                fieldMap.add(new EncryptFieldHandler(declaredField, encrypt.encryptor(), encrypt.toUpper()));
            }
            if (encrypt.onUpdate()) {
                List<AbsFieldHandler> fieldMap = clzMap.computeIfAbsent(SqlCommandType.UPDATE, k -> new ArrayList<>());
                fieldMap.add(new EncryptFieldHandler(declaredField, encrypt.encryptor(), encrypt.toUpper()));
            }
        }
    }

}
