
<delete id="batchDelete">
    delete from <include refid="table"/>
    <where>
        <include refid="condition"/>
        <#if entityMeta.deleteField??>
            and ${entityMeta.deleteField.columnName} = '${entityMeta.deleteField.no}'
        </#if>
    </where>
</delete>
