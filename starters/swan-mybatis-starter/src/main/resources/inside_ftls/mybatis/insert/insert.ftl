
<insert id="insert" <#if (entityMeta.autoIncId?string('true','false'))=="true"> useGeneratedKeys="true" keyProperty="${entityMeta.idField.propertyName}"</#if>>
    insert <include refid="table"/>(<include refid="columns"/>)
    values (
        ${r'#{'}${entityMeta.idField.propertyName}}
        <#list entityMeta.commonFields as field>
            ,${r'#{'}${field.propertyName}}
        </#list>
        <#if entityMeta.deleteField??>,'${entityMeta.deleteField.no}'</#if>
        <#if entityMeta.versionField??>,'${entityMeta.versionField.versionStart}'</#if>
    );
</insert>



