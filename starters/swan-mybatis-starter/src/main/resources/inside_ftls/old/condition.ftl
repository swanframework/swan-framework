

<foreach collection="oredCriteria" item="criteria" separator="or" >
    <if test="criteria.valid" >
        <trim prefix="(" suffix=")" prefixOverrides="and" >
            <foreach collection="criteria.criteria" item="criterion" >
                <choose >
                    <when test="criterion.noValue" >
                        and ${criterion.condition}
                    </when>
                    <when test="criterion.singleValue" >
                        and ${criterion.condition} #{criterion.value}
                    </when>
                    <when test="criterion.betweenValue" >
                        and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                    </when>
                    <when test="criterion.listValue" >
                        and ${criterion.condition}
                        <foreach collection="criterion.value" item="listItem" open="(" close=")" separator="," >
                            #{listItem}
                        </foreach>
                    </when>
                </choose>
            </foreach>
        </trim>
    </if>
</foreach>