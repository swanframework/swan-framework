package com.swan.poi.handler;

import com.swan.poi.anno.ExcelColumn;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.core.OrderComparator;

import java.util.List;
import java.util.Objects;

/**
 * @author zongf
 * @since 2023-05-17
 **/
public class ExcelCellHandlerChain {

    private List<ExcelCellHandler> cellHandlers;

    public ExcelCellHandlerChain(List<ExcelCellHandler> cellHandlers) {
        this.cellHandlers = cellHandlers;
    }

    public void setValue(Cell cell, Object value, ExcelColumn excelColumn) {
        if (Objects.isNull(value)) {
            return;
        }

        for (ExcelCellHandler cellHandler : cellHandlers) {
            boolean setSuccess = cellHandler.setValue(cell, value, excelColumn);

            if (setSuccess) {
                break;
            }
        }
    }





}
