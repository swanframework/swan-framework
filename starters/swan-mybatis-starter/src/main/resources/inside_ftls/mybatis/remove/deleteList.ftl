
<delete id="batchDelete">
    update <include refid="table"/>
    set
        ${entityMeta.idField.columnName} = ${entityMeta.idField.columnName}
        <#if entityMeta.deleteField??>
            ,${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.yes}'
        </#if>
        <#if entityMeta.versionField??>
            ,${entityMeta.versionField.columnName} = ${entityMeta.versionField.columnName} + 1
        </#if>
    <where>
        <include refid="condition"/>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
</delete>
