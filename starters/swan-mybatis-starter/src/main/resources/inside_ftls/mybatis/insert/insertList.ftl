
<insert id="insertList" <#if (entityMeta.autoIncId?string('true','false'))=="true"> useGeneratedKeys="true" keyProperty="${entityMeta.idField.propertyName}"</#if>>
    insert <include refid="tableName"/>(<include refid="columns"/>)
    values
    <foreach collection="list" separator="," item="entity">
        (
        ${r'#{entity.'}${entityMeta.idField.propertyName}}
        <#list entityMeta.commonFields as field>
            ,${r'#{entity.'}${field.propertyName}}
        </#list>
        <#if entityMeta.deleteField??>,'${entityMeta.deleteField.no}'</#if>
        <#if entityMeta.versionField??>,'${entityMeta.versionField.versionStart}'</#if>
        )
    </foreach>
</insert>