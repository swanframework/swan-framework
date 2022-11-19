package com.swan.generator.enums;

/** mysql所有数据类型
 * @author zongf
 * @since 2019-11-30
 */
public enum MysqlDataType {

    BIT("bit"),
    TINYINT("tinyint"),
    SMALLINT("smallint"),
    MEDIUMINT("mediumint"),
    INT("int"),
    BIGINT("bigint"),
    FLOAT("float"),
    DOUBLE("double"),
    DECIMAL("decimal"),
    DATE("date"),
    DATETIME("datetime"),
    TIMESTAMP("timestamp"),
    TIME("time"),
    YEAR("year"),
    CHAR("char"),
    VARCHAR("varchar"),
    VARBINARY("varbinary"),
    BINARY("binary"),
    TINYBLOB("tinyblob"),
    BLOB("blob"),
    MEDIUMBLOB("mediumblob"),
    LONGBLOB("longblob"),
    TINYTEXT("tinytext"),
    LONGTEXT("longtext"),
    TEXT("text"),
    MEDIUMTEXT("mediumtext"),
    ENUM("enum"),
    SET("set");

    private String type;

    MysqlDataType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
