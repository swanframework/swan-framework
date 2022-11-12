
<select id="count" resultType="int">
    select count(${entityMeta.idField.columnName})
    from <include refid="tableName"/>
    <where>
        <#if entityMeta.deleteField??>
            ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
</select>