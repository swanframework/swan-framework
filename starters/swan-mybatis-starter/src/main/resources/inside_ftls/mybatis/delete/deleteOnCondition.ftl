
<delete id="deleteOnCondition">
    delete from <include refid="tableName"/>
    <where>
        <if test="condition != null">
            <include refid="${entityMeta.conditionName}"/>
        </if>
    </where>
</delete>