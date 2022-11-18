<sql id="optionFields">
    <choose>
        <when test="options.length > 0 and options[0].columns.size > 0">
            <if test="options[0].distinct">
                distinct
            </if>
            <foreach collection="options[0].columns" separator="," item="column">
                ${r'${'}column}
            </foreach>
        </when>
        <otherwise>
            <include refid="columns"/>
        </otherwise>
    </choose>
</sql>