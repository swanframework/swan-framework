
<select id="queryAllIncludeColumns" resultType="${entityMeta.className}">
    select
        <trim suffixOverrides=",">
            <foreach collection="columnList" separator="," item="column" >
                ${r'${column}'}
            </foreach>
        </trim>
    from <include refid="tableName"/>
    <where>
        <#if entityMeta.deleteField??>
            ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
    order by
    <if test="orderByList.length > 0">
        <foreach collection="orderByList" separator="," item="rule">
            ${r'${rule.column} ${rule.type}'}
        </foreach>
    </if>
    <if test="orderByList.length == 0">
        ${entityMeta.idField.columnName} asc
    </if>
</select>