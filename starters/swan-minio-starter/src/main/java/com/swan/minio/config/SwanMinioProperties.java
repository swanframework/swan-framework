package com.swan.minio.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** minio 配置项
 * @author zongf
 * @date 2021-07-11
 */
@ConfigurationProperties(prefix = "swan.minio")
@Setter @Getter
public class SwanMinioProperties {

    /** minio 服务器配置 */
    private Server server = new Server();

    /** 是否注入 bucket 组件*/
    private boolean enableBucket;

    @Setter @Getter
    public static class Server {

        /** 服务器地址 */
        private String host;

        /** 服务器端口号 */
        private Integer port;

        /** 用户id */
        private String accessKey;

        /** 用户密码 */
        private String secretKey;

        /** 是否启用 https, 默认false */
        private boolean https;

        /** 域 */
        private String region;

    }

}
