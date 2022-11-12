
<delete id="delete">
    delete from <include refid="tableName"/>
    where ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
    <#if entityMeta.versionField??>
        and ${entityMeta.versionField.columnName} = ${r'#{'}${entityMeta.versionField.propertyName}}
    </#if>
</delete>