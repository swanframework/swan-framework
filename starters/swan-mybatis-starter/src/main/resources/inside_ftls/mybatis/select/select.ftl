
<select id="select" resultType="${entityMeta.className}">
    select <include refid="selectFields" />
    from <include refid="tableName"/>
    <where>
        <include refid="condition"/>
        <#if entityMeta.deleteField??>
           ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
    <include refid="selectOrders"/>
    <include refid="selectLimit"/>
</select>