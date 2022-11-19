package com.swan.generator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zongf
 * @date 2021-11-02
 */
@ConfigurationProperties(prefix = "sboot.generator.mybatis")
@Setter @Getter
public class SbootGeneratorMybatisProperties {

    /** 作者 */
    private String author;

    /** 项目根目录 */
    private String projectPath;

    /** 表前缀, 自动移除 */
    private String tablePrefix;

    /** 实体类 */
    private ClassConfig entity;

    /** condition 配置*/
    private ClassConfig condition;

    /** mapper 配置*/
    private MapperConfig mapper;

    @Setter @Getter
    public static class ClassConfig {

        /** 包名*/
        private String packageName;

        /** 生成类前缀, 自动添加 */
        private String namePrefix;

        /** 生成类后缀, 自动移除 */
        private String nameSuffix;

        /** 父类名称, 全类名 */
        private Class parentClass;

        /** @Table/@Id 默认包名*/
        private String annoPackage;


    }

    @Setter @Getter
    public static class MapperConfig extends ClassConfig {

        /** id 类型: Integer/Long/String*/
        private String idType;

        /** xml 文件存放目录 */
        private String xmlDir;
    }

}
