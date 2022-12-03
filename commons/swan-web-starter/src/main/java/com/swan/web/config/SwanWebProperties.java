package com.swan.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** Sboot 核心框架自动化配置
 * @author zongf
 * @since 2020-12-11
 */
@Setter @Getter
@ConfigurationProperties(prefix = "swan.web")
public class SwanWebProperties {

    /** 全局异常处理 */
    private GlobalException exception = new GlobalException();

    @Setter @Getter
    public static class GlobalException {

        /** 发生异常时, 响应状态是否为200, 默认为 true. 否则响应状态码为 5100*/
        private Boolean responseOk = true;

        /** 未知系统异常提示语 */
        private String unknownMessage = "未知系统异常";

        /** 校验异常模式: fast|full, fast 快速校验, full 全部字段校验*/
        private String validPattern = "fast";

        /** 是否转换 404 异常为 json 响应, 默认为 true. 相当于同时设置: <br/>
         *  spring.web.resources.add-mappings=false <br/>
         *  spring.mvc.throw-exception-if-no-handler-found: true
         */
        private boolean convert404 = true;
    }


}
