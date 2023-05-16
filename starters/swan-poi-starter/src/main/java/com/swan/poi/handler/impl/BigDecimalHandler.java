package com.swan.poi.handler.impl;

import cn.hutool.poi.excel.cell.CellHandler;
import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.handler.ExcelCellHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.core.Ordered;

import java.math.BigDecimal;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class BigDecimalHandler implements ExcelCellHandler {


    @Override
    public boolean setValue(Cell cell, Object value, ExcelColumn excelColumn) {

        boolean success = false;

        if (value instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) value;
            String str = bigDecimal.setScale(excelColumn.scale(), excelColumn.roundingMode()).toString();
            cell.setCellValue(str);
            success = true;
        }

        return success;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
