
<insert id="insertNotNull" <#if (entityMeta.autoIncId?string('true','false'))=="true"> useGeneratedKeys="true" keyProperty="${entityMeta.idField.propertyName}"</#if>>
    insert <include refid="table"/>(
        ${entityMeta.idField.columnName}
        <#list entityMeta.commonFields as field>
            <if test="${field.propertyName} != null ">,${field.columnName}</if>
        </#list>
        <#if entityMeta.deleteField??>
            ,${entityMeta.deleteField.columnName}
        </#if>
        <#if entityMeta.versionField??>
            ,${entityMeta.versionField.columnName}
        </#if>
    )
    values(
        ${r'#{'}${entityMeta.idField.propertyName}}
        <#list entityMeta.commonFields as field>
            <if test="${field.propertyName} != null ">,${r'#{'}${field.propertyName}}</if>
        </#list>
        <#if entityMeta.deleteField??>,'${entityMeta.deleteField.no}'</#if>
        <#if entityMeta.versionField??>,'${entityMeta.versionField.versionStart}'</#if>
    )
</insert>