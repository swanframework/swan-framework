
<delete id="deleteInIds">
    delete from <include refid="tableName"/>
    where ${entityMeta.idField.columnName} in
    <foreach collection="list" open="(" separator="," close=")" item="id">
        ${r'#{id}'}
    </foreach>
</delete>