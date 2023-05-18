package com.swan.poi.handler.impl;

import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.handler.ICellHandler;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class NumberCellHandler implements ICellHandler {


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
    public Object getValue(Cell cell, ExcelColumnInfo excelColumn) {

        if (excelColumn.getField().getType().isAssignableFrom(Byte.class)) {
            return (byte) cell.getNumericCellValue();
        }

        if (excelColumn.getField().getType().isAssignableFrom(Short.class)) {
            return (short) cell.getNumericCellValue();
        }

        if (excelColumn.getField().getType().isAssignableFrom(Integer.class)) {
            return (int) cell.getNumericCellValue();
        }

        if (excelColumn.getField().getType().isAssignableFrom(Long.class)) {
            return (long) cell.getNumericCellValue();
        }

        return null;
    }

    @Override
    public int getOrder() {
        return 12;
    }
}
