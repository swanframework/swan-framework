package com.swan.poi.config;

import com.swan.poi.handler.ICellHandler;
import com.swan.poi.handler.CellHandlerChain;
import com.swan.poi.service.IExcelTemplate;
import com.swan.poi.service.impl.ExcelTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;


@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ExcelConfig.class})
@Import(CellHandlerSelector.class)
public class SwanPoiAutoConfig {

    @Bean
    public IExcelTemplate excelTemplate() {
        return new ExcelTemplate();
    }

    @Bean
    public CellHandlerChain cellHandlerChain(List<ICellHandler> excelCellHandlers) {
        return new CellHandlerChain(excelCellHandlers);
    }

}

