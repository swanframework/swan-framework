
<select id="selectAll" resultType="${entityMeta.className}">
    select <include refid="columns"/>
    from <include refid="tableName"/>
    <where>
        <#if entityMeta.deleteField??>
           ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
    order by
        <if test="orderRules.length > 0">
            <foreach collection="orderRules" separator="," item="rule">
                ${r'${rule.column} ${rule.type}'}
            </foreach>
        </if>
        <if test="orderRules.length == 0">
            ${entityMeta.idField.columnName} asc
        </if>
</select>