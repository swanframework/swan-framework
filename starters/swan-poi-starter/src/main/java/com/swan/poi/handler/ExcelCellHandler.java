package com.swan.poi.handler;

import com.swan.poi.anno.ExcelColumn;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.core.Ordered;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public interface ExcelCellHandler extends Ordered {

    public boolean setValue(Cell cell, Object value, ExcelColumn excelColumn);

}
