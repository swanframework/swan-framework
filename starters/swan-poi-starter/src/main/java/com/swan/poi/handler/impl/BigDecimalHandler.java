package com.swan.poi.handler.impl;

import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.handler.IExcelCellHandler;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class BigDecimalHandler implements IExcelCellHandler {


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
    public Object getValue(Cell cell, ExcelColumnInfo excelColumn) {

        if (excelColumn.getField().getType().isAssignableFrom(BigDecimal.class)) {
            return new BigDecimal(cell.getStringCellValue());
        }

        return null;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
