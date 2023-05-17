package com.swan.poi.handler;

import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.domain.ExcelColumnInfo;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;
import java.util.Objects;

/**
 * @author zongf
 * @since 2023-05-17
 **/
public class ExcelCellHandlerChain{

    private List<IExcelCellHandler> cellHandlers;

    public ExcelCellHandlerChain(List<IExcelCellHandler> cellHandlers) {
        this.cellHandlers = cellHandlers;
    }

    public void setValue(Cell cell, Object value, ExcelColumn excelColumn) {
        if (Objects.isNull(value)) {
            return;
        }

        for (IExcelCellHandler cellHandler : cellHandlers) {
            boolean setSuccess = cellHandler.setValue(cell, value, excelColumn);

            if (setSuccess) {
                break;
            }
        }
    }

    public Object getValue(Cell cell, ExcelColumnInfo columnInfo) {

        Object value = null;
        for (IExcelCellHandler cellHandler : cellHandlers) {
            value = cellHandler.getValue(cell, columnInfo);

            if (Objects.nonNull(value)) {
                break;
            }
        }
        return value;
    }

}
