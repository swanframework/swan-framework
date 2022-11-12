<foreach collection="condition.whereList" item="criterion" separator="or" >
    <trim prefix="(" suffix=")" prefixOverrides="and" >
            <choose >
                <when test="criterion.noParamOp" >
                    and ${r'${'}criterion.column} ${r'${'}criterion.operator}
                </when>
                <when test="criterion.singleParamOp" >
                    and  ${r'${'}criterion.column} ${r'${'}criterion.operator} ${r'#{'}criterion.value}
                </when>
                <when test="criterion.secondParamOp" >
                    and  ${r'${'}criterion.column} ${r'${'}criterion.operator} ${r'#{'}criterion.value} and ${r'#{'}criterion.secondValue}
                </when>
                <when test="criterion.listParamOp" >
                    and  ${r'${'}criterion.column} ${r'${'}criterion.operator}
                    <foreach collection="criterion.listValues" item="itemValue" open="(" close=")" separator="," >
                        ${r'#{'}itemValue}
                    </foreach>
                </when>
            </choose>
    </trim>
</foreach>