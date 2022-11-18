<sql id="selectLimit">
    <if test="option.length > 0 and option[0].limit != null">
        limit
        <if test="option[0].offset">
            ${r'${optio[0]n.offset}'}
        </if>
        ${r'${option[0].limit}'}
    </if>
</sql>