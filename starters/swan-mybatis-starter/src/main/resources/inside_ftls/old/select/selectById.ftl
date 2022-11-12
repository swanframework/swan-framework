
<select id="selectById" resultType="${entityMeta.className}">
    select <include refid="columns"/>
    from <include refid="tableName"/>
    where ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
    <#if entityMeta.deleteField??>
        and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
    </#if>
</select>