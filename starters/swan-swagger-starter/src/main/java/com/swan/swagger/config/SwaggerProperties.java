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
@ConfigurationProperties(prefix = "sboot.swagger")
@Setter @Getter
public class SwaggerProperties {

    /** 是否开启 swagger */
    private boolean enable;

    /** 主页配置信息 */
    private HomeInfo info;

    /** 分组信息 */
    private List<Group> groups;

    /** 全局参数 */
    private List<Parameter> parameters;

    /** 路径前缀 */
    private String pathPrefix;

    /** 接口扫描路径 */
    private String[] scanPackages;

    @Data
    public static class HomeInfo{

        /** 标题 */
        private String title = "接口文档";

        /** 简介 */
        private String description = "接口文档";

        /** 版本号 */
        private String version = "1.0";

        /** 服务地址 */
        private String serviceUrl = "http://localhost";

        /** 作者 */
        private String author = "sboot";

    }

    @Data
    public static class Parameter{

        /** 参数名称 */
        private String name;

        /** 参数类型 */
        private String type;

        /** 参数描述 */
        private String description;

        /** 参数默认值 */
        private String defaultValue;

        /** 参数位置: header|query| */
        private String location;

        /** 是否必填 */
        private boolean required;

    }

    @Data
    public static class Group {

        /** 匹配路径 */
        private String pathPattern;

        /** 分组名称 */
        private String name;

        /** 描述 */
        private String description;

        /** 参数 */
        private List<Parameter> parameters;

    }

}
