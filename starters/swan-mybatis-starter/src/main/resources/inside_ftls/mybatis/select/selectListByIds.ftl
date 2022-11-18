
<select id="selectListByIds" resultType="${entityMeta.className}">
    select <include refid="selectFields" />
    from <include refid="tableName"/>
    where ${entityMeta.idField.columnName} in
        <foreach collection="idList" open="(" separator="," close=")" item="id">
            ${r'#{id}'}
        </foreach>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    <include refid="selectOrders"/>
</select>