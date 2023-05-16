package com.swan.generator.service.impl;


import com.swan.core.utils.DateUtil;
import com.swan.core.utils.TxtFileUtil;
import com.swan.freemarker.core.IFreemarkerTemplate;
import com.swan.generator.config.SbootGeneratorMybatisProperties;
import com.swan.generator.constants.FtlConstant;
import com.swan.generator.enums.CommentTypeEnum;
import com.swan.generator.enums.GenerateTypeEnum;
import com.swan.generator.generator.EntityMetaCreator;
import com.swan.generator.generator.EntityMetaHandler;
import com.swan.generator.service.api.IMybatisGeneratorService;
import com.swan.generator.util.PathUtil;
import com.swan.generator.vo.EntityMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zongf
 * @since 2021-03-01
 */
public class MybatisGeneratorService implements IMybatisGeneratorService {

    @Autowired
    private EntityMetaCreator entityMetaCreator;

    @Autowired
    private SbootGeneratorMybatisProperties config;

    @Autowired
    @Qualifier("freemarkerTemplateInside")
    private IFreemarkerTemplate freemarkerTemplateInside;

    @Override
    public EntityMeta generateEntity(String schemaName, String tableName, CommentTypeEnum commentTypeEnum) {

        // entity 生成配置信息
        SbootGeneratorMybatisProperties.ClassConfig entityConfig = config.getEntity();

        // 查询表信息,生成对应的 java 信息
        EntityMeta metaInfo = entityMetaCreator.queryEntityMetaInfo(schemaName, tableName);
        metaInfo.setPackageName(entityConfig.getPackageName());

        // 处理类名
        String entityName = EntityMetaHandler.handleClassName(metaInfo, entityConfig, config.getTablePrefix());
        metaInfo.setClassName(entityName);

        // 处理继承关系
        EntityMetaHandler.handleInheritFields(metaInfo, entityConfig);

        // 处理import类
        EntityMetaHandler.handleFieldTypeImport(metaInfo);
        EntityMetaHandler.handleDefaultImport(metaInfo, entityConfig, GenerateTypeEnum.ENTITY, commentTypeEnum);

        // 根据模板生成代码
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("commentType", commentTypeEnum.code());
        dataMap.put("author", config.getAuthor());
        dataMap.put("config", config.getEntity());
        dataMap.put("metaInfo", metaInfo);
        dataMap.put("createDate", DateUtil.getToday());

        // 生成代码
        String code = freemarkerTemplateInside.getContent(FtlConstant.MYBATIS_ENTITY, dataMap);

        // 文件路径
        String filePath = PathUtil.getClassPath(config.getProjectPath(), entityConfig.getPackageName(), metaInfo.getClassName());

        // 写入文件
        TxtFileUtil.writeFile(filePath,code);

        return metaInfo;
    }

    @Override
    public String generateCondition(String schemaName, String tableName) {

        // condition 生成配置信息
        SbootGeneratorMybatisProperties.ClassConfig conditionConfig = config.getCondition();

        // 查询表信息,生成对应的 java 信息
        EntityMeta metaInfo = entityMetaCreator.queryEntityMetaInfo(schemaName, tableName);
        metaInfo.setPackageName(config.getEntity().getPackageName());

        // condition 需要导入 ICondition 接口
        List<String> importList = new ArrayList<>();
        metaInfo.setImportList(importList);
        EntityMetaHandler.handleDefaultImport(metaInfo, config.getCondition(), GenerateTypeEnum.CONDITION, CommentTypeEnum.NONE);

        // 处理类名
        String conditionName = EntityMetaHandler.handleClassName(metaInfo, config.getCondition(), config.getTablePrefix());
        metaInfo.setClassName(conditionName);

        // 根据模板生成代码
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("author", this.config.getAuthor());
        dataMap.put("config", config.getCondition());
        dataMap.put("metaInfo", metaInfo);
        dataMap.put("createDate", DateUtil.getToday());

        // 生成代码
        String code = freemarkerTemplateInside.getContent(FtlConstant.MYBATIS_CONDITION, dataMap);

        // 获取文件路径
        String filePath = PathUtil.getClassPath(config.getProjectPath(), config.getCondition().getPackageName(), metaInfo.getClassName());

        // 写入文件
        TxtFileUtil.writeFile(filePath, code);

        return filePath;
    }

    @Override
    public String generateMapper(String schemaName, String tableName) {

        // mapper 生成配置信息
        SbootGeneratorMybatisProperties.MapperConfig mapperConfig = config.getMapper();

        // 查询表信息,生成对应的 java 信息
        EntityMeta entityMetaInfo = entityMetaCreator.queryEntityMetaInfo(schemaName, tableName);
        entityMetaInfo.setPackageName(config.getMapper().getPackageName());

        // 生成 Condition, Entity, Mapper 名称
        String entityName =  EntityMetaHandler.handleClassName(entityMetaInfo, config.getEntity(), config.getTablePrefix());
        String conditionName =  EntityMetaHandler.handleClassName(entityMetaInfo, config.getCondition(), config.getTablePrefix());
        String mapperName = EntityMetaHandler.handleClassName(entityMetaInfo, mapperConfig, config.getTablePrefix());
        entityMetaInfo.setClassName(mapperName);

        // 判断是否需要导入 entity 类: entity 和 condition 不在一个包则需要导入
        if (!config.getEntity().getPackageName().equals(mapperConfig.getPackageName())) {
            entityMetaInfo.getImportList().add(config.getEntity().getPackageName() + "." + entityName);
        }

        // 处理默认导入的包
        EntityMetaHandler.handleDefaultImport(entityMetaInfo, mapperConfig, GenerateTypeEnum.MAPPER, CommentTypeEnum.NONE);

        // 判断是否需要导入父 Mapper 的包
        if (mapperConfig.getParentClass() != null) {
            String parentMapperPackage = mapperConfig.getParentClass().getPackage().getName();
            if (!parentMapperPackage.equals(mapperConfig.getPackageName())) {
                entityMetaInfo.getImportList().add(mapperConfig.getParentClass().getTypeName());
            }
            entityMetaInfo.getImportList().add(config.getCondition().getPackageName() + "." + conditionName);
        }

        // 根据模板生成代码
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("author", this.config.getAuthor());
        dataMap.put("config", mapperConfig);
        dataMap.put("metaInfo", entityMetaInfo);
        dataMap.put("createDate", DateUtil.getToday());
        if (mapperConfig.getParentClass() != null) {
            dataMap.put("parentMapperName", mapperConfig.getParentClass().getSimpleName());
        }
        dataMap.put("entityName", entityName);
        dataMap.put("conditionName", conditionName);

        // 生成代码
        String codeList = freemarkerTemplateInside.getContent(FtlConstant.MYBATIS_MAPPER, dataMap);

        // 获取文件路径
        String filePath = PathUtil.getClassPath(config.getProjectPath(),mapperConfig.getPackageName(), entityMetaInfo.getClassName());

        // 写入文件
        TxtFileUtil.writeFile(filePath, codeList);

        return filePath;
    }

    @Override
    public String generateMapperXml(String schemaName, String tableName) {

        // 查询表信息,生成对应的 java 信息
        EntityMeta metaInfo = entityMetaCreator.queryEntityMetaInfo(schemaName, tableName);
        metaInfo.setPackageName(config.getMapper().getPackageName());

        // 生成 Condition, Entity, Mapper 名称
        String mapperName =  EntityMetaHandler.handleClassName(metaInfo, config.getMapper(), config.getTablePrefix());
        String conditionName =  EntityMetaHandler.handleClassName(metaInfo, config.getCondition(), config.getTablePrefix());
        String entityName =  EntityMetaHandler.handleClassName(metaInfo, config.getEntity(), config.getTablePrefix());
        metaInfo.setClassName(entityName);

        // 根据模板生成代码
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("mapperName", config.getMapper().getPackageName() + "." + mapperName);
        dataMap.put("conditionName", conditionName);
        dataMap.put("entityMeta", metaInfo);
        dataMap.put("entityPackageName", config.getEntity().getPackageName());

        // 生成代码
        String codeList = freemarkerTemplateInside.getContent(FtlConstant.MYBATIS_MAPPER_XML, dataMap);

        // 获取文件路径
        String filePath = PathUtil.getXmlPath(config.getProjectPath(), config.getMapper().getXmlDir(), mapperName);

        // 写入文件
        TxtFileUtil.writeFile(filePath, codeList);

        return filePath;
    }

}
