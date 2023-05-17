package com.swan.poi.service.impl;

import com.swan.poi.config.SwanPoiProperties;
import com.swan.poi.handler.ExcelCellHandlerChain;
import com.swan.poi.service.IExcelService;
import com.swan.poi.utils.PoiExcelReader;
import com.swan.poi.utils.PoiExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Slf4j
public class ExcelService implements IExcelService {

    // 内存中缓存记录数

    @Autowired
    private SwanPoiProperties swanPoiProperties;

    @Autowired
    private ExcelCellHandlerChain excelCellHandlerChain;

    @Override
    public <T> Workbook write(String sheetName, List<T> data, Class<T> clz) {

        PoiExcelWriter poiExcelWriter = new PoiExcelWriter(clz, sheetName, swanPoiProperties, excelCellHandlerChain);

        Workbook wb = poiExcelWriter.write(data);

        return wb;
    }

    @Override
    public <T> List<T> read(InputStream is, Class<T> clz, Integer sheetNum) {

        PoiExcelReader poiExcelReader = new PoiExcelReader(excelCellHandlerChain);
        return poiExcelReader.parse(is, clz, sheetNum);
    }






}
