<sql id="selectOrders">
    order by
    <choose>
        <when test="option.length > 0 and option[0].orderBys.size > 0">
            <foreach collection="option[0].orderBys" separator="," item="rule">
                ${r'${rule.column} ${rule.type}'}
            </foreach>
        </when>
        <otherwise>
            ${entityMeta.idField.columnName} asc
        </otherwise>
    </choose>
</sql>