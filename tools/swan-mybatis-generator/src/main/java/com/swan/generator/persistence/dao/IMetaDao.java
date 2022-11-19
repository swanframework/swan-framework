package com.swan.generator.persistence.dao;

import com.swan.generator.persistence.po.ColumnPO;
import com.swan.generator.persistence.po.SchemaPO;
import com.swan.generator.persistence.po.TablePO;

import java.util.List;

/**
 * @author zongf
 * @date 2021-11-02
 */
public interface IMetaDao {


    /** 数据库schemas 查询
     * @return List<SchemaPO>
     * @author zongf
     * @date 2019-11-30
     */
    public List<SchemaPO> querySchemas();


    /** 数据库表信息查询
     * @param schemas 数据库名
     * @return List<TablePO>
     * @since 1.0
     * @author zongf
     * @created 2019-11-30
     */
    public List<TablePO> queryTables(String schemas);

    /** 数据库表信息查询
     * @param schemaName 数据库名
     * @return List<TablePO>
     * @since 1.0
     * @author zongf
     * @created 2019-11-30
     */
    public TablePO queryTable(String schemaName, String tableName);

    /** 查询表字段信息
     * @param schemaName 数据库名
     * @param tableName 表名
     * @return List<ColumnPO>
     * @author zongf
     * @date 2019-11-30
     */
    public List<ColumnPO> queryColumns(String schemaName, String tableName);

}
