
<update id="updateNotNull">
    update <include refid="table"/>
    set
        ${entityMeta.idField.columnName} = ${entityMeta.idField.columnName}
        <#list entityMeta.commonFields as field>
            <if test="${field.propertyName} != null ">
                ,${field.columnName} = ${r'#{'}${field.propertyName}}
            </if>
        </#list>
        <#if entityMeta.versionField??>
            ,${entityMeta.versionField.columnName} = ${entityMeta.versionField.columnName} + 1
        </#if>
    where
        ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
        <#if entityMeta.versionField??>
            and ${entityMeta.versionField.columnName} = ${r'#{'}${entityMeta.versionField.propertyName}}
        </#if>
</update>