package com.swan.poi.service.impl;

import com.swan.poi.cache.ExcelCache;
import com.swan.poi.config.SwanPoiProperties;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.service.IExcelService;
import com.swan.poi.utils.PoiExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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

    @Override
    public <T> Workbook write(String sheetName, List<T> data, Class<T> clz) {

        PoiExcelWriter poiExcelWriter = new PoiExcelWriter(clz, sheetName, swanPoiProperties);

        Workbook wb = poiExcelWriter.write(data);

        return wb;
    }

    @Override
    public <T> List<T> read(InputStream is, Class<T> clz) {
        return null;
    }






}
