<sql id="condition">
    <if test="condition != null and condition.criterionList.size > 0">
        and (
        <trim prefixOverrides = "and|or">
            <foreach collection="condition.criterionList" item="criterion" >
                <if test="criterion.operatorParamType == 0">
                    ${r'${'}criterion.logicOp} ${r'${'}criterion.column} ${r'${'}criterion.operator}
                </if>
                <if test="criterion.operatorParamType == 1">
                    ${r'${'}criterion.logicOp} ${r'${'}criterion.column} ${r'${'}criterion.operator} ${r'#{'}criterion.value}
                </if>
                <if test="criterion.operatorParamType == 2">
                    ${r'${'}criterion.logicOp} ${r'${'}criterion.column} ${r'${'}criterion.operator} ${r'#{'}criterion.value} and ${r'#{'}criterion.secondValue}
                </if>
                <if test="criterion.operatorParamType == 3">
                    ${r'${'}criterion.logicOp} ${r'${'}criterion.column} ${r'${'}criterion.operator}
                    <foreach collection="criterion.values" item="itemValue" open="(" close=")" separator="," >
                        ${r'#{'}itemValue}
                    </foreach>
                </if>
            </foreach>
        </trim>
        )
    </if>
</sql>