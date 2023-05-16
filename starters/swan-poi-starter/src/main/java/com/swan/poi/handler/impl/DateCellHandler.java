package com.swan.poi.handler.impl;

import com.swan.core.utils.DateUtil;
import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.handler.ExcelCellHandler;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class DateCellHandler implements ExcelCellHandler {


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
    public int getOrder() {
        return 20;
    }
}
