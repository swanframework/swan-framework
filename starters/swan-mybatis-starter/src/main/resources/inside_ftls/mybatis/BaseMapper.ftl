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
        <#include "./condition.ftl" />
    </sql>


    <!-- 新增方法 -->
    <#if methodsInfo.methodNames?seq_contains("insert")>
        <#include "insert/insert.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("insertNotNull")>
        <#include "insert/insertNotNull.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("insertList")>
        <#include "insert/insertList.ftl"/>
    </#if>

    <!-- 删除方法 -->
    <#if methodsInfo.methodNames?seq_contains("delete")>
        <#if entityMeta.deleteField??>
            <#include "remove/delete.ftl"/>
        <#else >
            <#include "delete/delete.ftl"/>
        </#if>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("deleteList")>
        <#if entityMeta.deleteField??>
            <#include "remove/deleteList.ftl"/>
        <#else >
            <#include "delete/deleteList.ftl"/>
        </#if>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("deleteById")>
        <#if entityMeta.deleteField??>
            <#include "remove/deleteById.ftl"/>
        <#else >
            <#include "delete/deleteById.ftl"/>
        </#if>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("deleteByIds")>
        <#if entityMeta.deleteField??>
            <#include "remove/deleteByIds.ftl"/>
        <#else >
            <#include "delete/deleteByIds.ftl"/>
        </#if>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("deleteOnCondition")>
        <#if entityMeta.deleteField??>
            <#include "remove/deleteOnCondition.ftl"/>
        <#else >
            <#include "delete/deleteOnCondition.ftl"/>
        </#if>
    </#if>


    <!-- 更新方法 -->
    <#if methodsInfo.methodNames?seq_contains("update")>
        <#include "update/update.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateById")>
        <#include "update/updateById.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateFieldsById")>
        <#include "update/updateFieldsById.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateFields")>
        <#include "update/updateFields.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateNotNullById")>
        <#include "update/updateNotNullById.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateNotNull")>
        <#include "update/updateNotNull.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateOnCondition")>
        <#include "update/updateOnCondition.ftl"/>
    </#if>

    <!-- 查询方法 -->

    <#if methodsInfo.methodNames?seq_contains("selectById")>
        <#include "select/selectById.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("selectOnCondition")>
        <#include "select/selectOnCondition.ftl"/>
    </#if>

    <#if methodsInfo.methodNames?seq_contains("selectListByIds")>
        <#include "select/selectListByIds.ftl"/>
    </#if>

    <#if methodsInfo.methodNames?seq_contains("selectListOnCondition")>
        <#include "select/selectListOnCondition.ftl"/>
    </#if>

    <#if methodsInfo.methodNames?seq_contains("selectFieldsById")>
        <#include "select/selectFieldsById.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("selectFieldsByIds")>
        <#include "select/selectFieldsByIds.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("selectFieldsOnCondition")>
        <#include "select/selectFieldsOnCondition.ftl"/>
    </#if>

    <#if methodsInfo.methodNames?seq_contains("selectAll")>
        <#include "select/selectAll.ftl"/>
    </#if>

    <!-- 统计方法 -->
    <#if methodsInfo.methodNames?seq_contains("count")>
        <#include "count/count.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("countOnCondition")>
        <#include "count/countOnCondition.ftl"/>
    </#if>

</mapper>
