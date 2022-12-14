
<select id="selectListByIds" resultType="${entityMeta.className}">
    select <include refid="optionFields" />
    from <include refid="table"/>
    where ${entityMeta.idField.columnName} in
        <foreach collection="idList" open="(" separator="," close=")" item="id">
            ${r'#{id}'}
        </foreach>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    <include refid="optionOrders"/>
    <include refid="optionLimit"/>
</select>