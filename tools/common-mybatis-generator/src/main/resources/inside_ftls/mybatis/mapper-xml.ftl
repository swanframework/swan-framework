<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${mapperName}">

    <sql id="table">
        ${entityMeta.tableName}
    </sql>

    <sql id="columns">
        <#list entityMeta.fields as field>${field.columnName}<#sep>, </#list>
    </sql>

    <sql id="condition">

    </sql>

    <insert id="insert" >
        insert <include refid="table"/>(<include refid="columns"/>)
        values (
        <#list entityMeta.fields as field>
            ${r'#{'}${field.name}}<#sep>,
        </#list>

        );
    </insert>

    <delete id="deleteById">
        delete from <include refid="table"/>
        where id = ${r'#{id}'}
    </delete>

    <update id="updateById">
        update <include refid="table"/>
        set
        <#list entityMeta.fields as field>
            ${field.columnName} = ${r'#{'}${field.name}}<#sep>,
        </#list>

        where id = ${r'#{id}'}
    </update>

    <update id="updateNotNullById">
        update <include refid="table"/>
        set
        <#list entityMeta.fields as field>
            <if test="${field.name} != null ">
                ${field.columnName} = ${r'#{'}${field.name}}<#sep>,</#sep>
            </if>
        </#list>

        where id = ${r'#{id}'}
    </update>

    <select id="selectById" resultType="${entityMeta.packageName}.${entityMeta.className}" >
        select <include refid="columns" />
        from <include refid="table" />
        where id = ${r'#{id}'}
    </select>

    <select id="select" resultType="${entityMeta.packageName}.${entityMeta.className}" >
        select <include refid="columns" />
        from <include refid="table" />
        <where>
            <include refid="condition"/>
        </where>
    </select>

    <select id="selectList" resultType="${entityMeta.packageName}.${entityMeta.className}" >
        select <include refid="columns" />
        from <include refid="table" />
        <where>
            <include refid="condition"/>
        </where>
    </select>

</mapper>
