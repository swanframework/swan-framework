package com.swan.poi.handler.impl;

import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.handler.ExcelCellHandler;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class DefaultCellHandler implements ExcelCellHandler {

    @Override
    public boolean setValue(Cell cell, Object value, ExcelColumn excelColumn) {
        cell.setCellValue("" + value);
        return true;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
