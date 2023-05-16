package com.swan.poi.config;

import com.swan.poi.service.IExcelService;
import com.swan.poi.service.impl.ExcelService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({SwanPoiProperties.class})
public class SwanPoiAutoConfig {

    @Bean
    public IExcelService excelService() {
        return new ExcelService();
    }

}


/**

 @Excel
 public class AgentDO{

    @Column(title="",
    private String name;

 }



 Wb = ExcelUtil.generate(doList,Config);

 // 1.创建 wb
 // 2.创建 sheet
 // 3.创建 row
 // 4.创建 cell
        字段处理器: cellHandler
        字段处理器: cellHandler








 */
