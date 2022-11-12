<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${namespace}">

    <sql id="tableName">
        ${entityMeta.tableName}
    </sql>

    <sql id="columns">
        ${entityMeta.idField.columnName}
        <#list entityMeta.commonFields as field>
             ,${field.columnName}
        </#list>
        <#if entityMeta.deleteField??>,${entityMeta.deleteField.columnName}</#if>
        <#if entityMeta.versionField??>,${entityMeta.versionField.columnName}</#if>
    </sql>

    <sql id="WhereCondition">
        <#if entityMeta.deleteField??>
            ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </sql>

    <!-- 新增方法 -->
    <#if (methodsInfo.insert?string('true','false'))=="true">
        <#include "insert/insert.ftl"/>
    </#if>
    <#if (methodsInfo.insertNotNull?string('true','false'))=="true">
        <#include "insert/insertNotNull.ftl"/>
    </#if>
    <#if (methodsInfo.insertList?string('true','false'))=="true">
        <#include "insert/insertList.ftl"/>
    </#if>

    <!-- 物理删除方法 -->
    <#if (methodsInfo.delete?string('true','false'))=="true">
        <#include "delete/delete.ftl"/>
    </#if>
    <#if (methodsInfo.deleteById?string('true','false'))=="true">
        <#include "delete/deleteById.ftl"/>
    </#if>
    <#if (methodsInfo.deleteInIds?string('true','false'))=="true">
        <#include "delete/deleteInIds.ftl"/>
    </#if>
    <#if (methodsInfo.deleteList?string('true','false'))=="true">
        <#include "delete/deleteList.ftl"/>
    </#if>
    <#if (methodsInfo.deleteOnCondition?string('true','false'))=="true">
        <#include "delete/deleteOnCondition.ftl"/>
    </#if>

    <!-- 逻辑删除方法 -->
    <#if (methodsInfo.remove?string('true','false'))=="true">
        <#include "remove/remove.ftl"/>
    </#if>
    <#if (methodsInfo.removeById?string('true','false'))=="true">
        <#include "remove/removeById.ftl"/>
    </#if>
    <#if (methodsInfo.removeInIds?string('true','false'))=="true">
        <#include "remove/removeInIds.ftl"/>
    </#if>
    <#if (methodsInfo.removeList?string('true','false'))=="true">
        <#include "remove/removeList.ftl"/>
    </#if>
    <#if (methodsInfo.removeOnCondition?string('true','false'))=="true">
        <#include "remove/removeOnCondition.ftl"/>
    </#if>

    <!-- 更新方法 -->
    <#if (methodsInfo.update?string('true','false'))=="true">
        <#include "update/update.ftl"/>
    </#if>
    <#if (methodsInfo.updateById?string('true','false'))=="true">
        <#include "update/updateById.ftl"/>
    </#if>
    <#if (methodsInfo.updateNotNullById?string('true','false'))=="true">
        <#include "update/updateNotNullById.ftl"/>
    </#if>
    <#if (methodsInfo.updateNotNull?string('true','false'))=="true">
        <#include "update/updateNotNull.ftl"/>
    </#if>

    <!-- 查询方法 -->
    <#if (methodsInfo.selectAll?string('true','false'))=="true">
        <#include "select/selectAll.ftl"/>
    </#if>
    <#if (methodsInfo.selectById?string('true','false'))=="true">
        <#include "select/selectById.ftl"/>
    </#if>
    <#if (methodsInfo.selectListInIds?string('true','false'))=="true">
        <#include "select/selectListInIds.ftl"/>
    </#if>
    <#if (methodsInfo.selectListOnCondition?string('true','false'))=="true">
        <#include "select/selectListOnCondition.ftl"/>
    </#if>

    <!-- 统计方法 -->
    <#if (methodsInfo.count?string('true','false'))=="true">
        <#include "count/count.ftl"/>
    </#if>
    <#if (methodsInfo.countOnCondition?string('true','false'))=="true">
        <#include "count/countOnCondition.ftl"/>
    </#if>

</mapper>
