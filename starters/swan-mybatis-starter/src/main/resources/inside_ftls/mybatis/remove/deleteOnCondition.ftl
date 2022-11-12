
<delete id="deleteOnCondition">
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
        <if test="condition != null">
            <include refid="${entityMeta.conditionName}"/>
        </if>
    </where>
</delete>