package com.swan.poi.config;

import com.swan.poi.handler.ExcelCellHandler;
import com.swan.poi.handler.ExcelCellHandlerChain;
import com.swan.poi.handler.impl.*;
import com.swan.poi.service.IExcelService;
import com.swan.poi.service.impl.ExcelService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({SwanPoiProperties.class})
@Import({BigDecimalHandler.class, DoubleCellHandler.class, NumberCellHandler.class, DateCellHandler.class, DefaultCellHandler.class})
public class SwanPoiAutoConfig {

    @Bean
    public IExcelService excelService() {
        return new ExcelService();
    }

    @Bean
    public ExcelCellHandlerChain excelCellHandlerChain(List<ExcelCellHandler> excelCellHandlers) {
        return new ExcelCellHandlerChain(excelCellHandlers);
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
