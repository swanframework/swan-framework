package com.swan.swagger.config;

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

    /** 是否启用 knife4j 生成文档*/
    private boolean enable;

    /** 主页-标题  */
    private String title;

    /** 主页显示-描述 */
    private String description;

    /** 主页显示-版本 */
    private String version;

    /** 主页显示-接口域名 */
    private String host;

    /** 主页显示-服务地址 */
    private String serviceUrl;

    /** 主页显示-作者 */
    private String author;

    /** 全部分组配置，必须要配置 **/
    private Group global;

    /** 接口分组配置 */
    private List<Group> groups;

    /** 声明参数配置 */
    private List<ParameterConfig> parameterConfigs;

    @Setter @Getter
    public static class ParameterConfig {

        /** 参数名 */
        private String name;

        /** 参数位置，可选其一: header|query|cookie|path  */
        private ParameterLocation location;

        /** 参数描述 */
        private String description;

        /** 是否必填 */
        private boolean required;

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
        /** 分组名称，ui分组下拉框中按此名字进行自然排序，但ui中并不展示*/
        private String name;

        /** ui 显示名称 */
        private String displayName;

        /** 通用的参数名称，需要先声名，多个参数用英文逗号分割*/
        private List<String> commonParameters;

        /** 过滤规则 **/
        private Filter filter;

    }

    /** 过滤规则 **/
    @Setter @Getter
    static class Filter {

        /** 扫描包, 多个包以英文逗号分割*/
        private String[] packagesToScan;

        /** 排除要扫描的包, 多个包以英文逗号分割*/
        private String[] packagesToExclude;

        /** 扫描目录, 多个路径以英文逗号分割*/
        private String[] pathsToMatch;

        /** 排除要扫描的目录, 多个路径以英文逗号分割*/
        private String[] pathsToExclude;

        /** 请求要包含的 header */
        private String[] headersToMatch;

    }
}
