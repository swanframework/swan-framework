package com.swan.generator.generator;

import com.swan.generator.config.SbootGeneratorMybatisProperties;
import com.swan.generator.enums.CommentTypeEnum;
import com.swan.generator.enums.GenerateTypeEnum;
import com.swan.generator.enums.JavaMappingType;
import com.swan.generator.persistence.po.ColumnPO;
import com.swan.generator.util.NameUtil;
import com.swan.generator.util.ReflectorUtil;
import com.swan.generator.vo.EntityFieldMeta;
import com.swan.generator.vo.EntityMeta;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author zongf
 * @since 2021-03-25
 */
public class EntityMetaHandler {

    /** 处理表字段信息
     * @param metaVO  po 模型
     * @author zongf
     * @since 2019-11-30
     */
    public static void handleColumns(EntityMeta metaVO, List<ColumnPO> columnPOList) {
        // 解析字段
        for (ColumnPO columnPO : columnPOList) {
            EntityFieldMeta fieldVO = new EntityFieldMeta();
            fieldVO.setType(getJavaType(columnPO).toString());
            fieldVO.setComment(columnPO.getComment());
            fieldVO.setName(NameUtil.toHumpName(columnPO.getColumnName()));
            fieldVO.setColumnName(columnPO.getColumnName());
            fieldVO.setJdbcType(columnPO.getDataType().toString());
            fieldVO.setPkColumn(columnPO.isPKColumn());
            metaVO.getFields().add(fieldVO);
        }
    }

    /** 处理字段类型是否需要导入依赖
     * @param metaVO po 模型
     * @author zongf
     * @since 2019-11-30
     */
    public static void handleFieldTypeImport(EntityMeta metaVO) {
        // 获取需要导入的类型
        Set<String> importSet = new HashSet<>();
        for (EntityFieldMeta fieldVO : metaVO.getFields()) {
            String importType = getImportType(fieldVO.getType());
            if(importType != null) importSet.add(importType);
        }
        metaVO.getImportList().addAll(importSet);
    }


    /** 获取需要导入依赖
     * @param javaMappingType
     * @return String
     * @author zongf
     * @since 2019-11-30
     */
    public static String getImportType(String javaMappingType) {
        switch (javaMappingType) {
            case "BigDecimal":
                return "java.math.BigDecimal";
            case "BigInteger":
                return "java.math.BigInteger";
            case "Date":
                return "java.util.Date";
        }
        return null;
    }

    /** 设置java 类型
     * @param columnPO
     * @author zongf
     * @since 2019-11-30
     * @return JavaMappingType
     */
    public static JavaMappingType getJavaType(ColumnPO columnPO) {
        boolean unsigned = columnPO.getColunmType().contains("unsigned");
        return DefaultTypeConverter.getType(columnPO.getDataType(), unsigned, columnPO.getMaxIntDigits());
    }

    /** 生成类名称
     * @param metaVO 元数据信息
     * @param config 配置
     * @param tablePrefix 表名前缀
     * @return String
     * @author zongf
     * @since 2021-11-03
     */
    public static String handleClassName(EntityMeta metaVO, SbootGeneratorMybatisProperties.ClassConfig config, String tablePrefix) {

        // 处理前缀
        StringBuffer nameSb = new StringBuffer();
        if (config.getNamePrefix() != null) {
            nameSb.append(NameUtil.firstUppercase(config.getNamePrefix()));
        }
        String tableName = metaVO.getTableName();
        if (StringUtils.hasText(tablePrefix)) {
            int idx = metaVO.getTableName().lastIndexOf(tablePrefix);
            tableName = metaVO.getTableName().substring(idx + tablePrefix.length() + 1);
        }

        nameSb.append(NameUtil.firstUppercase(NameUtil.toHumpName(tableName)));

        // 处理后缀
        if (config.getNameSuffix() != null) {
            nameSb.append(NameUtil.firstUppercase(config.getNameSuffix().trim()));
        }

        return nameSb.toString();
    }

    /** 处理导入包
     * @param entityMetaInfo
     * @param generatorConfig
     * @author zongf
     * @since 2021-03-01
     */
    public static void handleDefaultImport(EntityMeta entityMetaInfo, SbootGeneratorMybatisProperties.ClassConfig generatorConfig,
                                           GenerateTypeEnum generateType, CommentTypeEnum commentType){

        List<String> imports = entityMetaInfo.getImportList();

        // 处理 lombok 依赖
        if ((GenerateTypeEnum.ENTITY.equals(generateType) || (GenerateTypeEnum.CONDITION.equals(generateType)))) {
            imports.add("lombok.Getter");
            imports.add("lombok.Setter");
        }

        // 处理 swagger 依赖
        if (CommentTypeEnum.SWAGGER.equals(commentType) || CommentTypeEnum.DOC_AND_SWAGGER.equals(commentType)) {
            imports.add("io.swagger.annotations.ApiModelProperty");
        }

        // 如果父类和当前类不在同一个包中, 则添加依赖
        if (generatorConfig.getParentClass() != null && !generatorConfig.getPackageName().equals(generatorConfig.getPackageName())) {
            imports.add(generatorConfig.getParentClass().getCanonicalName());
        }

        String annoPackage = StringUtils.hasText(generatorConfig.getAnnoPackage())
                ? generatorConfig.getAnnoPackage() : "com.swan.mybatis";
        // 处理 mybatis 依赖
        if (GenerateTypeEnum.ENTITY.equals(generateType)) {
            imports.add(annoPackage + ".anno.Table");
            for (EntityFieldMeta field : entityMetaInfo.getFields()) {
                if (field.isPkColumn()) {
                    imports.add(annoPackage + ".anno.Id");
                    break;
                }
            }
        }
    }

    /** 处理字段继承，移除父类定义的字段
     * @param entityMetaInfo
     * @param config
     * @author zongf
     * @since 2021-03-01
     */
    public static void handleInheritFields(EntityMeta entityMetaInfo,  SbootGeneratorMybatisProperties.ClassConfig config) {
        if (config.getParentClass() != null) {
            Set<String> allDeclareFields = ReflectorUtil.getAllDeclareFields(config.getParentClass());
            Iterator<EntityFieldMeta> iterator = entityMetaInfo.getFields().iterator();
            while (iterator.hasNext()) {
                String fieldName = iterator.next().getName();
                if (allDeclareFields.contains(fieldName)) {
                    iterator.remove();
                }
            }
        }
    }

}
