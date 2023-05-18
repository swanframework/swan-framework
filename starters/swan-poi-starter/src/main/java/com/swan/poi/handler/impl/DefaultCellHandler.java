package com.swan.poi.handler.impl;

import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.handler.ICellHandler;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class DefaultCellHandler implements ICellHandler {

    @Override
    public boolean setValue(Cell cell, Object value, ExcelColumn excelColumn) {
        cell.setCellValue("" + value);
        return true;
    }

    @Override
    public Object getValue(Cell cell, ExcelColumnInfo columnInfo) {

        return cell.getStringCellValue();
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
