
<delete id="deleteByIds">
    delete from <include refid="table"/>
    where ${entityMeta.idField.columnName} in
    <foreach collection="list" open="(" separator="," close=")" item="id">
        ${r'#{id}'}
    </foreach>
</delete>