<sql id="optionOrders">
    order by
    <choose>
        <when test="options.length > 0 and options[0].orderBys.size > 0">
            <foreach collection="options[0].orderBys" separator="," item="rule">
                ${r'${rule.column} ${rule.type}'}
            </foreach>
        </when>
        <otherwise>
            ${entityMeta.idField.columnName} asc
        </otherwise>
    </choose>
</sql>