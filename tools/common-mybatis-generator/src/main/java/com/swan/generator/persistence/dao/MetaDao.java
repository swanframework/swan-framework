package com.swan.generator.persistence.dao;

import com.swan.generator.enums.MysqlDataType;
import com.swan.generator.exception.CodesGeneratorException;
import com.swan.generator.persistence.po.ColumnPO;
import com.swan.generator.persistence.po.SchemaPO;
import com.swan.generator.persistence.po.TablePO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** 通过jdbc 方法
 * @author zongf
 * @since 2019-11-30
 */
public class MetaDao implements IMetaDao {

    @Autowired
    private DataSource dataSource;

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    @Override
    public List<SchemaPO> querySchemas() {

        String sql = "select * from information_schema.schemata";

        ResultSet rs = excuteQuery(getConnection(), sql);

        try {
            List<SchemaPO> schemaPOList = new ArrayList<>();
            while (rs.next()) {
                SchemaPO schemaPO = new SchemaPO();
                schemaPO.setCatalogName(rs.getString("CATALOG_NAME"));
                schemaPO.setSchemaName(rs.getString("SCHEMA_NAME"));
                schemaPO.setDefaultCharacterName(rs.getString("DEFAULT_CHARACTER_SET_NAME"));
                schemaPO.setDefaultCollationName(rs.getString("DEFAULT_COLLATION_NAME"));
                schemaPO.setSqlPath(rs.getString("SQL_PATH"));
                schemaPOList.add(schemaPO);
            }

            return schemaPOList.size() > 0 ? schemaPOList : null;
        } catch (SQLException ex) {
            throw new RuntimeException("结果集解析异常", ex);
        }
    }

    @Override
    public List<TablePO> queryTables(String schemaName) {

        String sql = "select * from information_schema.tables where table_schema = ?";

        ResultSet rs = excuteQuery(getConnection(), sql, schemaName);

        try {
            List<TablePO> tablePOList = new ArrayList<>();
            while (rs.next()) {
                TablePO tablePO = new TablePO();
                tablePO.setTableName(rs.getString("TABLE_NAME"));
                tablePO.setComment(rs.getString("TABLE_COMMENT"));
                tablePO.setEngine(rs.getString("ENGINE"));
                tablePO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                tablePOList.add(tablePO);
            }
            return tablePOList.size() > 0 ? tablePOList : null;
        } catch (SQLException ex) {
            throw new RuntimeException("结果集解析异常", ex);
        }
    }

    @Override
    public TablePO queryTable(String schemaName, String tableName) {
        String sql = "select * from information_schema.tables where table_schema = ? and table_name = ?";

        ResultSet rs = excuteQuery(getConnection(), sql, schemaName, tableName);

        try {
            if(rs.next()){
                TablePO tablePO = new TablePO();
                tablePO.setTableName(rs.getString("TABLE_NAME"));
                tablePO.setComment(rs.getString("TABLE_COMMENT"));
                tablePO.setEngine(rs.getString("ENGINE"));
                tablePO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                return tablePO;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("结果集解析异常", ex);
        }
        return null;
    }

    @Override
    public List<ColumnPO> queryColumns(String schemaName, String tableName) {

        String sql = "select * from information_schema.columns where table_schema = ? and table_name = ? order by ordinal_position";

        ResultSet rs = excuteQuery(getConnection(), sql, schemaName, tableName);

        try {
            List<ColumnPO> columnPOList = new ArrayList<>();
            while (rs.next()) {
                ColumnPO columnPO = new ColumnPO();
                columnPO.setColumnName(rs.getString("COLUMN_NAME"));
                columnPO.setColunmType(rs.getString("COLUMN_TYPE"));
                columnPO.setDataType(MysqlDataType.valueOf(rs.getString("DATA_TYPE").toUpperCase()));
                columnPO.setPosition(rs.getInt("ORDINAL_POSITION"));
                columnPO.setMaxCharLength(rs.getLong("CHARACTER_MAXIMUM_LENGTH"));
                columnPO.setMaxIntDigits(rs.getInt("NUMERIC_PRECISION"));
                columnPO.setMaxDecimalDigits(rs.getInt("NUMERIC_SCALE"));
                columnPO.setComment(rs.getString("COLUMN_COMMENT"));
                columnPO.setDefaultValue(rs.getString("COLUMN_DEFAULT"));
                columnPO.setAutoIncrement("auto_increment".equals(rs.getString("EXTRA")));
                columnPO.setNullAble("YES".equals(rs.getString("IS_NULLABLE")));
                columnPO.setPKColumn("PRI".equals(rs.getString("COLUMN_KEY")));
                columnPOList.add(columnPO);
            }
            return columnPOList.size() > 0 ? columnPOList : null;
        } catch (SQLException ex) {
            throw new RuntimeException("结果集解析异常", ex);
        }
    }

    /** 数据库查询
     * @param conn 数据库链接
     * @param sql 查询sql
     * @param params 参数
     * @return ResultSet
     * @author zongf
     * @since 2019-11-30
     */
    private ResultSet excuteQuery(Connection conn, String sql , Object... params){
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i+1, params[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("数据库查询失败", e);
        }
    }

}
