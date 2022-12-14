
<update id="updateById">
    update <include refid="table"/>
    set
        ${entityMeta.idField.columnName} = ${entityMeta.idField.columnName}
        <#list entityMeta.commonFields as field>
            ,${field.columnName} = ${r'#{'}${field.propertyName}}
        </#list>
        <#if entityMeta.versionField??>
            ,${entityMeta.versionField.columnName} = ${entityMeta.versionField.columnName} + 1
        </#if>
    where
        ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
</update>