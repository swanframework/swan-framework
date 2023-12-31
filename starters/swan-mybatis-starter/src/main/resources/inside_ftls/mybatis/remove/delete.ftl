
<delete id="delete">
    update <include refid="table"/>
    set
        ${entityMeta.idField.columnName} = ${entityMeta.idField.columnName}
        <#if entityMeta.deleteField??>
            ,${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.yes}'
        </#if>
        <#if entityMeta.versionField??>
            ,${entityMeta.versionField.columnName} = ${entityMeta.versionField.columnName} + 1
        </#if>
    where
        ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
        <#if entityMeta.versionField??>
            and ${entityMeta.versionField.columnName} = ${r'#{'}${entityMeta.versionField.propertyName}}
        </#if>
</delete>