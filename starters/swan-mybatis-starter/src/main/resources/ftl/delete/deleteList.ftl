
<delete id="deleteList">
    delete from <include refid="tableName"/>
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
    </where>

</delete>