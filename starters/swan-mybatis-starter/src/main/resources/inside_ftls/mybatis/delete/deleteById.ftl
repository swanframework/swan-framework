
<delete id="deleteById">
    delete from <include refid="tableName"/>
    where ${entityMeta.idField.columnName} = ${r'#{'}${entityMeta.idField.propertyName}}
</delete>