package com.swan.freemarker.config;

import com.swan.freemarker.core.FreemarkerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/** freemarker 自动化配置
 *      1. freemarkerTemplate: 模板目录为 spring.freemarker.template-loader-path 配置的
 *      2. freemarkerTemplateInside:
 *          1)模板目录为 classpath:/ 的根目录, 即 src/main/resources
 *          2)主要供内部封装的框架, starter 等使用, 统一放到 ftls_inside 目录, 然后调用是使用 ftls_inside/xxx 前缀,
 *            防止与用户自定义模板冲突
 *      3. 需要注意的是, freemarkerTemplate 和 freemarkerTemplateInside 共用一个模板缓存, 缓存key为相对路径名称，因此需要有规范
 *
 *
 * @author zongf
 * @date 2021-07-29
 */
@Configuration
public class SwanFreeMarkerAutoConfig {

    // spring 会在 FreeMarkerNonWebConfiguration 中实现注入
    @Autowired
    private freemarker.template.Configuration configuration;

    @Bean
    @Primary
    public FreemarkerTemplate freemarkerTemplate() {
        // 使用 springboot 默认的配置, 模板目录为 spring.freemarker.template-loader-path 配置
        return new FreemarkerTemplate(configuration);
    }

    @Bean
    public FreemarkerTemplate freemarkerTemplateInside() {
        // 复制 spring 的配置文件, 手动更改模板目录
        freemarker.template.Configuration insideConfiguration  = (freemarker.template.Configuration) this.configuration.clone();

        // 修改目录为根目录
        String baseFtlPath = "inside_ftls";
        insideConfiguration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), baseFtlPath);
        return new FreemarkerTemplate(insideConfiguration);
    }

}
