
<delete id="deleteById">
    delete from <include refid="table"/>
    where ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
</delete>