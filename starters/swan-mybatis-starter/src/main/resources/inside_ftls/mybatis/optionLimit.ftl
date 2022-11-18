<sql id="optionLimit">
    <if test="options.length > 0 and options[0].limit != null">
        limit
        <if test="options[0].offset">
            ${r'${optio[0]n.offset}'}
        </if>
        ${r'${options[0].limit}'}
    </if>
</sql>