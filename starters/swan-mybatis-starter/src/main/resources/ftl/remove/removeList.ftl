
<delete id="removeList">
    update <include refid="tableName"/>
    set
        ${entityMeta.idField.columnName} = ${entityMeta.idField.columnName}
        <#if entityMeta.deleteField??>
            ,${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.yes}'
        </#if>
        <#if entityMeta.versionField??>
            ,${entityMeta.versionField.columnName} = ${entityMeta.versionField.columnName} + 1
        </#if>
    <where>
        <#if entityMeta.versionField??>
            and
            <foreach collection="list" open="(" separator="or" close=")" item="entity">
                (
                    ${entityMeta.idField.columnName} = ${r'#{entity.'}${entityMeta.idField.propertyName}}
                    and ${entityMeta.versionField.columnName} = ${r'#{entity.'}${entityMeta.versionField.propertyName}}
                )
            </foreach>
        <#else >
            ${entityMeta.idField.columnName} in
            <foreach collection="list" open="(" separator="," close=")" item="entity">
                ${r'#{entity.'}${entityMeta.idField.propertyName}}
            </foreach>
        </#if>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>

</delete>