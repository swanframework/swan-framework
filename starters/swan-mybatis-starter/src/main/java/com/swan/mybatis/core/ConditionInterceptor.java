package com.swan.mybatis.core;

import com.swan.core.exception.SwanBaseException;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import com.swan.core.utils.ReflectUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 用自定义 mapper 中的 condition 片段，替换为内置的 condition 片段，已废弃
 * @author zongf
 * @since 2020-01-07
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
@Deprecated
public class ConditionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation)  throws Throwable {
        Object[] args = invocation.getArgs();

        MappedStatement mappedStatement = (MappedStatement) args[0];

        mappedStatement.getConfiguration();

        Object param = args[1];
        if (param instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) args[1];

            Class<?> declaringClass = invocation.getMethod().getDeclaringClass();
            System.out.println("声明类:" + declaringClass);

            Object pm = paramMap.get("condition");

                if (pm instanceof ICondition) {
                    String conditionName = pm.getClass().getSimpleName();

                    String conditionSqlFragmentName = this.getConditionSqlFragmentName(mappedStatement.getConfiguration(), conditionName);

                    Map<String, XNode> sqlFragments = mappedStatement.getConfiguration().getSqlFragments();

                    XNode context = sqlFragments.get(conditionSqlFragmentName);


                    LanguageDriver langDriver = mappedStatement.getLang();
                    SqlSource conditionSqlSource = langDriver.createSqlSource(mappedStatement.getConfiguration(), context, pm.getClass());


                    SqlSource sqlSource = mappedStatement.getSqlSource();

                    if (sqlSource instanceof DynamicSqlSource) {
                        DynamicSqlSource dynamicSqlSource = (DynamicSqlSource) sqlSource;
                        SqlNode rootSqlNode = (SqlNode) ReflectUtil.getFieldValue(dynamicSqlSource, "rootSqlNode");

                        if (rootSqlNode instanceof MixedSqlNode) {
                            List<SqlNode> nodeList = (List<SqlNode>) ReflectUtil.getFieldValue(rootSqlNode, "contents");

                            for (SqlNode sqlNode : nodeList) {
                                if (sqlNode instanceof WhereSqlNode) {

                                    Class<?> superclass = sqlNode.getClass().getSuperclass();
                                    Field spContents = superclass.getDeclaredField("contents");
                                    spContents.setAccessible(true);
                                    Object contents = spContents.get(sqlNode);

                                    if (contents instanceof MixedSqlNode) {
                                        List<SqlNode> list = (List<SqlNode>) ReflectUtil.getFieldValue(contents, "contents");

                                        if (conditionSqlSource instanceof DynamicSqlSource) {
                                            SqlNode snode = (SqlNode) ReflectUtil.getFieldValue(conditionSqlSource, "rootSqlNode");
                                            list.add(snode);
                                        }
                                    }

                                }

                            }

                        }
                    }


                    System.out.println("find...");


                }
        }

        System.out.println("拦截到查询....");


        return invocation.proceed();
    }




    private String getConditionSqlFragmentName(Configuration configuration, String conditionName) {
        Map<String, XNode> sqlFragments = configuration.getSqlFragments();

        // 按condition的简单类名,搜索是否已自定义xml片段
        List<String> conditionSqlList = new ArrayList<>();
        for (String sqlId : sqlFragments.keySet()) {
            if (sqlId.endsWith("." + conditionName)) {
                conditionSqlList.add(sqlId);
            }
        }

        // 如果未自定义, 则使用自动生成的xml中的空的<sql id="condition"></sql>.
        // 如果只找到一个以Condition 简单类名结尾的<sql id="xxCondition"></sql> 则返回
        // 如果找到多个自定义的,则抛出异常
        if (conditionSqlList.isEmpty()) {
            return "condition";
        }else if(conditionSqlList.size() == 1) {
            return conditionSqlList.get(0);
        }else {
            String errMsg = "请保证条件类名称唯一:" + conditionName;
            throw new SwanBaseException(errMsg);
        }
    }

}