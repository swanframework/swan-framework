package com.swan.poi.handler.impl;

import com.swan.core.utils.DateUtil;
import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.handler.ICellHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.util.Date;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class DateCellHandler implements ICellHandler {


    @Override
    public boolean setValue(Cell cell, Object value, ExcelColumn excelColumn) {

        boolean success = false;

        if (value instanceof Date) {
            Date date = (Date) value;
            cell.setCellValue(DateUtil.format(date, excelColumn.dateFormat()));
            success = true;
        }

        return success;
    }

    @Override
    public Object getValue(Cell cell, ExcelColumnInfo excelColumn) {

        CellType cellType = cell.getCellType();
        if (excelColumn.getField().getType().isAssignableFrom(Date.class)) {
            return DateUtil.parseDate(cell.getStringCellValue());
        }

        return null;
    }

    @Override
    public int getOrder() {
        return 20;
    }
}
