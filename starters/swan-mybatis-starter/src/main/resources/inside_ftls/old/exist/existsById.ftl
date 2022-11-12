
<select id="existsById" resultType="boolean">
    select count(${entityMeta.idField.columnName})
    from <include refid="tableName"/>
    where ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
    <#if entityMeta.deleteField??>
        and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
    </#if>
</select>