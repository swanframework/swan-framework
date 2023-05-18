package com.swan.poi.service.impl;

import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.cache.ExcelColumnCache;
import com.swan.poi.config.ExcelConfig;
import com.swan.poi.exception.SwanPoiException;
import com.swan.poi.handler.CellHandlerChain;
import com.swan.poi.service.IExcelTemplate;
import com.swan.poi.utils.ExcelUtil;
import com.swan.poi.utils.PoiExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Slf4j
public class ExcelTemplate implements IExcelTemplate {

    @Autowired
    private ExcelConfig excelConfig;

    @Autowired
    private CellHandlerChain cellHandlerChain;

    @Override
    public <T> Workbook exportData(String sheetName, List<T> data, Class<T> clz) {

        PoiExcelWriter poiExcelWriter = new PoiExcelWriter(clz, sheetName, excelConfig, cellHandlerChain);

        Workbook wb = poiExcelWriter.write(data);

        return wb;
    }

    @Override
    public <T> List<T> importData(InputStream is, Class<T> clz, Integer startRow) {

        // 校验是否存在 excel 注解
        if (!ExcelColumnCache.hasExcelAnnoField(clz)) {
            String message = String.format("%s 中未使用 %s 定义列信息", clz.getSimpleName(), ExcelColumn.class);
            throw new SwanPoiException(ExceptionCodeEnum.POI.code(), message);
        }

        // 创建 workBook
        Workbook wb = ExcelUtil.createWorkBook(is);

        List<T> dataList = new ArrayList<>();
        for (int sheetIdx = 0; sheetIdx < wb.getNumberOfSheets(); sheetIdx++) {
            // 获取sheet 页
            Sheet sheet = wb.getSheetAt(sheetIdx);

            // 读取 sheet 数据
            List<T> subList = ExcelUtil.readSheet(sheet, clz, startRow);

            if (!subList.isEmpty()) {
                dataList.addAll(subList);
            }
        }

        return dataList;
    }


}
