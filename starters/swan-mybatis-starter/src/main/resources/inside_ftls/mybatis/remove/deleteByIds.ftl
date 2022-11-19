
<delete id="deleteByIds">
    update <include refid="tableName"/>
    set
        ${entityMeta.idField.columnName} = ${entityMeta.idField.columnName}
        <#if entityMeta.deleteField??>
            ,${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.yes}'
        </#if>
        <#if entityMeta.versionField??>
            ,${entityMeta.versionField.columnName} = ${entityMeta.versionField.columnName} + 1
        </#if>
    where ${entityMeta.idField.columnName} in
        <foreach collection="list" open="(" separator="," close=")" item="id">
            ${r'#{id}'}
        </foreach>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
</delete>