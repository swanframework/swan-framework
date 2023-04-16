package com.swan.generator.generator;

import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.generator.exception.CodesGeneratorException;
import com.swan.generator.persistence.dao.IMetaDao;
import com.swan.generator.persistence.po.ColumnPO;
import com.swan.generator.persistence.po.TablePO;
import com.swan.generator.vo.EntityMeta;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** 实体信息生成器
 * @author zongf
 * @since 2019-11-30
 */
public class EntityMetaCreator {

    @Autowired
    private IMetaDao metaDao;

    /** 获取Athm po 信息
     * @param schemaName  数据库名称
     * @param tableName  表名
     * @return EntityMetaInfo
     * @author zongf
     * @since 2019-11-30
     */
    public EntityMeta queryEntityMetaInfo(String schemaName, String tableName) {

        // 查询表详情
        TablePO tablePO = metaDao.queryTable(schemaName, tableName);

        // 如果查询表不存，则返回 null
        if(tablePO == null) {
            throw new CodesGeneratorException(ExceptionCodeEnum.FREEMARKER.code(), " 表不存在, 数据库:" + schemaName + ", 表名:" + tableName);
        }

        List<ColumnPO> columnPOList = metaDao.queryColumns(schemaName, tableName);

        // 处理表基本信息
        EntityMeta entityMetaInfo = new EntityMeta();
        entityMetaInfo.setClassComment(tablePO.getComment());
        entityMetaInfo.setTableName(tableName);

        // 处理字段信息
        EntityMetaHandler.handleColumns(entityMetaInfo, columnPOList);

        return entityMetaInfo;
    }


}
