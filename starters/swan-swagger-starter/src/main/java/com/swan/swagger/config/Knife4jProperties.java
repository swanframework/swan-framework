package com.swan.swagger.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**配置
 * @author zongf
 * @date 2021-07-21
 */
@ConfigurationProperties(prefix = "knife4j")
@Setter @Getter
public class Knife4jProperties {

    /** 是否启用 knife4j 文档*/
    private boolean enable;

    /** 接口文档标题  */
    private String title;

    /** 接口文档描述 */
    private String description;

    /** 接口文档版本 */
    private String version;

    /** 接口域名 */
    private String host;

    /** 服务地址 */
    private String serviceUrl;

    /** 作者 */
    private String author;

    /** 全局参数 */
    private List<String> globalParameters;

    /** 分组 */
    private List<Group> groups;

    /** 定义参数 */
    private List<Parameter> parameters;

    @Setter @Getter
    public static class Parameter{

        /** 参数名 */
        private String name;
        /** 参数位置 */
        private ParameterLocation location;
        /** 参数描述 */
        private String description;
        /** 是否必填 */
        private boolean required;
        /** 默认值 */
        private String defaultValue;
        /** 是否允许为空*/
        private boolean allowEmpty;

    }

    @Getter
    enum ParameterLocation {
        /** header 中 */
        HEADER("header"),
        /** request param */
        QUERY("query"),
        /** url path 中 */
        PATH("path"),
        /** cookie 中*/
        COOKIE("cookie");

        private final String location;

        ParameterLocation(String location) {
            this.location = location;
        }
    }

    @Setter @Getter
    static class Group{
        /** 分组名称，ui 按此名字进行分组排序*/
        private String name;

        /** ui 显示名称 */
        private String displayName;

        /** 扫描包, 多个包以英文逗号分割*/
        private String[] packagesToScan;

        /** 排除要扫描的包, 多个包以英文逗号分割*/
        private String[] packagesToExclude;

        /** 扫描目录, 多个路径以英文逗号分割*/
        private String[] pathsToMatch;

        /** 排除要扫描的目录, 多个路径以英文逗号分割*/
        private String[] pathsToExclude;

        /** 通用的参数名称，需要先定义，多个参数用英文逗号分割*/
        private List<String> commonParameters;

    }
}
