package com.swan.log.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** swan 日志配置
 * @author zongf
 * @since 2022-11-07
 **/
@ConfigurationProperties(prefix = "swan.log")
@Setter @Getter
public class SwanLogProperty {

    /** 参数最大长度 */
    private Integer argMaxLength;

    /** 日志最大长度 */
    private Integer maxLength;

    /** 超长后缀，默认为:... */
    private String overrideSuffix = "...";

    /** 日志存放目录 */
    private String dir;

}
