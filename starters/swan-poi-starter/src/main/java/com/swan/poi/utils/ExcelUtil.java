package com.swan.poi.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.core.utils.ReflectUtil;
import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.cache.ExcelColumnCache;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.exception.SwanPoiException;
import com.swan.poi.handler.CellHandlerChain;
import com.swan.poi.handler.ICellHandler;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zongf
 * @since 2023-05-18
 **/
public class ExcelUtil {

    public static CellHandlerChain chain;

    public static CellHandlerChain getCellHandlerChain() {
        if (chain == null) {
            chain = SpringUtil.getBean(CellHandlerChain.class);
        }
        return chain;
    }

    public static Workbook createWorkBook(InputStream is) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(is);
        } catch (IOException e) {
            throw new SwanPoiException(ExceptionCodeEnum.POI.code(), "创建 Excel 异常");
        }
        return wb;
    }

    public static <T> List<T> readSheet(Sheet sheet,  Class<T> clz, Integer startRow) {
        List<ExcelColumnInfo> columnInfos = ExcelColumnCache.getColumns(clz);

        // 未指定起始行号，则从第一行开始读取
        if (startRow == null || startRow < 1) {
            startRow = 1;
        }

        List<T> dataList = new ArrayList<>();

        for (int rowIdx = startRow; rowIdx < sheet.getLastRowNum(); rowIdx++) {
            Row row = sheet.getRow(rowIdx);

            T instance = ReflectUtil.newInstance(clz);

            int columnIdx = 0;
            for (ExcelColumnInfo columnInfo : columnInfos) {

                Cell cell = row.getCell(columnIdx++);

                ExcelColumn excelColumn = columnInfo.getExcelColumn();

                Object value = null;

                // 自定义 cell 解析器时，尝试使用自定义解析器解析
                if (StringUtils.hasText(excelColumn.handler())) {
                    ICellHandler handler = SpringUtil.getBean(excelColumn.handler(), ICellHandler.class);
                    if (Objects.isNull(handler)) {
                        String message = String.format("未找到名称为 %s 的", ICellHandler.class.getSimpleName());
                        throw new SwanPoiException(ExceptionCodeEnum.POI.code(), message);
                    } else if (handler != null) {
                        value = handler.getValue(cell, columnInfo);
                    }
                } else {
                    // 使用默认解析器链解析 cell
                    value = getCellHandlerChain().getValue(cell, columnInfo);
                }

                // 设置对象属性
                if (value != null) {
                    ReflectUtil.setFieldValue(instance,columnInfo.getField(),  value);
                }

                dataList.add(instance);
            }
        }
        return dataList;
    }
}
