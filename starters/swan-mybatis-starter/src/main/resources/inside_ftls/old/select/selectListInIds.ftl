
<select id="selectListInIds" resultType="${entityMeta.className}">
    select <include refid="columns"/>
    from <include refid="tableName"/>
    where ${entityMeta.idField.columnName} in
        <foreach collection="idList" open="(" separator="," close=")" item="id">
            ${r'#{id}'}
        </foreach>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
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