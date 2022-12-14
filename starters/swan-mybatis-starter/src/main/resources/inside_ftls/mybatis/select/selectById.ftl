
<select id="selectById" resultType="${entityMeta.className}">
    select<include refid="optionFields" />
    from <include refid="table"/>
    where ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
    <#if entityMeta.deleteField??>
        and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
    </#if>
    <include refid="optionOrders"/>
    <include refid="optionLimit"/>
</select>