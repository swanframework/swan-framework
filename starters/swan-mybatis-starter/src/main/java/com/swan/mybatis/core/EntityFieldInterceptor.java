package com.swan.mybatis.core;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import com.swan.mybatis.mapper.field.handler.AbsFieldHandler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/** 字段拦截器
 * @author zongf
 * @since 2020-01-07
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }) })
public class EntityFieldInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation)  throws Throwable {

        // 获取参数列表: 第0个参数为 mappedStatement, 第一个参数为参数信息
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object param = args[1];

        // 需要拦截的sql语句类型
        List<SqlCommandType> commandTypes = Arrays.asList(SqlCommandType.INSERT, SqlCommandType.UPDATE, SqlCommandType.DELETE);

        // 只拦截 insert/update/delete 语句
        if (commandTypes.contains(mappedStatement.getSqlCommandType())) {
            this.handleFields(mappedStatement, param);
        }

        return invocation.proceed();
    }

    /** 对字段的值进行修改
     * @param mappedStatement
     * @param mixParams 可能为单个对象, 也可能是ParamMap
     * @author zongf
     * @since 2021-01-08
     */
    private void handleFields(MappedStatement mappedStatement, Object mixParams) {
        // 如果无参数, 则返回
        if(mixParams == null) return;

        Class typeClass;

        // 如果参数为List 或 使用@Param 定义了别名, 则参数类型为 ParamMap. 此时取Map中的第一个元素即可. 因为增删改包括批量, 都只需要一个参数即可
        // 示例: insert(List<E> list);
        //      insert(@Param("entity") E e);
        if (mixParams instanceof MapperMethod.ParamMap) {

            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) mixParams;

            if (paramMap.containsKey("list")){
                mixParams = paramMap.get("list");
            } else if (paramMap.containsKey("collection")) {
                mixParams = paramMap.get("collection");
            } else if (paramMap.containsKey("array")) {
                mixParams = paramMap.get("array");
            } else {
                for (Object value : paramMap.values()) {
                    if (EntityFieldParser.CACHE_MAP.containsKey(value.getClass())) {
                        mixParams = value;
                        break;
                    }
                }
            }
        }

        // 传的是 List, 获取参数的真正类型, 如: insert(List<E> list)
        if (mixParams instanceof Collection) {
            Collection collection = (Collection) mixParams;
            if(collection == null || collection.isEmpty()) return;
            typeClass = collection.iterator().next().getClass();
        }else {
            // 方法示例: insert(E e)
            typeClass = mixParams.getClass();
        }

        Map<SqlCommandType, List<AbsFieldHandler>> clzMap = EntityFieldParser.CACHE_MAP.get(typeClass);
        // 如果通过class在缓存中不存在, 则证明此类型未使用@Table修饰, 不进行解析
        if (clzMap == null) return;

        List<AbsFieldHandler> fieldHandlers = clzMap.get(mappedStatement.getSqlCommandType());

        // 如果获取不到, 则标识此种命令(insert/update/delete) 对此对象无需修改
        if(fieldHandlers == null) return;

        // 如果方法为批量操作(批量插入/批量保存/批量更新)
        if (mixParams instanceof Collection) {
            for (Object entity : ((Collection) mixParams)) {
                for (AbsFieldHandler fieldHandler : fieldHandlers) {
                    fieldHandler.updateField(entity);
                }
            }
        }else {
            // 如果方法为单个操作
            for (AbsFieldHandler fieldHandler : fieldHandlers) {
                fieldHandler.updateField(mixParams);
            }
        }
    }

}