<sql id="selectFields">
    <choose>
        <when test="option.length > 0 and option[0].columns.size > 0">
            <if test="option[0].distinct">
                distinct
            </if>
            <foreach collection="option[0].columns" separator="," item="column">
                ${r'${'}column}
            </foreach>
        </when>
        <otherwise>
            <include refid="columns"/>
        </otherwise>
    </choose>
</sql>