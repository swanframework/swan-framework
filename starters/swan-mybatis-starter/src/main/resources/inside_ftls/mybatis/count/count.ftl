
<select id="count" resultType="int">
    select count(*)
    from <include refid="tableName"/>
    <where>
        <include refid="condition"/>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
</select>