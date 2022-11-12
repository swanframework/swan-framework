
<select id="countOnCondition" resultType="int">
    select count(${entityMeta.idField.columnName})
    from <include refid="tableName"/>
    <where>
        <if test="condition != null">
            <include refid="${entityMeta.conditionName}"/>
        </if>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
</select>