
<select id="selectList" resultType="${entityMeta.className}">
    select <include refid="optionFields" />
    from <include refid="tableName"/>
    <where>
        <include refid="condition"/>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
    <include refid="optionOrders"/>
    <include refid="optionLimit"/>
</select>