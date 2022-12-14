<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${namespace}">

    <sql id="table">
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

    <!-- 查询条件 -->
    <#include "condition.ftl" />

    <!-- 查询字段 -->
    <#include "optionFields.ftl" />

    <!-- 搜索排序 -->
    <#include "optionOrders.ftl" />

    <!-- 分页 -->
    <#include "optionLimit.ftl" />

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
    <#if methodsInfo.methodNames?seq_contains("deleteByIds")>
        <#if entityMeta.deleteField??>
            <#include "remove/deleteByIds.ftl"/>
        <#else >
            <#include "delete/deleteByIds.ftl"/>
        </#if>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("deleteById")>
        <#if entityMeta.deleteField??>
            <#include "remove/deleteById.ftl"/>
        <#else >
            <#include "delete/deleteById.ftl"/>
        </#if>
    </#if>

    <!-- 更新方法 -->
    <#if methodsInfo.methodNames?seq_contains("update")>
        <#include "update/update.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateById")>
        <#include "update/updateById.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateNotNullById")>
        <#include "update/updateNotNullById.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("updateNotNull")>
        <#include "update/updateNotNull.ftl"/>
    </#if>

    <!-- 查询方法 -->

    <#if methodsInfo.methodNames?seq_contains("selectById")>
        <#include "select/selectById.ftl"/>
    </#if>
    <#if methodsInfo.methodNames?seq_contains("selectListByIds")>
        <#include "select/selectListByIds.ftl"/>
    </#if>

    <#if methodsInfo.methodNames?seq_contains("select")>
        <#include "select/select.ftl"/>
    </#if>

    <#if methodsInfo.methodNames?seq_contains("selectList")>
        <#include "select/selectList.ftl"/>
    </#if>

    <!-- 统计方法 -->
    <#if methodsInfo.methodNames?seq_contains("count")>
        <#include "count/count.ftl"/>
    </#if>

</mapper>
