package com.swan.poi.handler.impl;

import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.handler.ExcelCellHandler;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class NumberCellHandler implements ExcelCellHandler {


    @Override
    public boolean setValue(Cell cell, Object value, ExcelColumn excelColumn) {

        boolean success = false;

        if (value instanceof Number) {
            cell.setCellValue(Double.valueOf(String.valueOf(value)));
            success = true;
        }

        return success;
    }

    @Override
    public int getOrder() {
        return 12;
    }
}
