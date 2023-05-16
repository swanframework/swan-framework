package com.swan.poi.utils;

import com.swan.core.utils.DateUtil;
import com.swan.core.utils.ReflectUtil;
import com.swan.poi.cache.ExcelCache;
import com.swan.poi.config.SwanPoiProperties;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.handler.ExcelCellHandlerChain;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class PoiExcelWriter {

    private Workbook wb;

    // sheet 页名称
    private String sheetName;

    // 字段信息
    private List<ExcelColumnInfo> columnInfos;

    // 配置
    private SwanPoiProperties swanPoiProperties;

    // 内存中缓存记录数
    private static final Integer rowAccessWindowSize = 500;

    // sheet页最大记录数
    private static final int maxSheetSize = 65536;


    private ExcelCellHandlerChain chain;

    public PoiExcelWriter(Class clz, String sheetName
            , SwanPoiProperties swanPoiProperties, ExcelCellHandlerChain chain) {
        this.sheetName = sheetName;
        this.swanPoiProperties = swanPoiProperties;
        this.chain = chain;

        this.wb = new SXSSFWorkbook(rowAccessWindowSize);

        this.columnInfos = ExcelCache.get(clz);
    }

    public <T> Workbook write(List<T> list) {
        return write(list, maxSheetSize);
    }

    public <T> Workbook write(List<T> list, Integer sheetSize) {

        // 计算 sheet 页数
        int sheetNums = Math.max(1, (int) Math.ceil(list.size() * 1.0 / sheetSize));

        for (int sheetIdx = 0; sheetIdx < sheetNums; sheetIdx++) {
            // 1.创建sheet
            Sheet sheet = wb.createSheet();
            wb.setSheetName(sheetIdx, sheetName + "_" + sheetIdx );

            // 2.计算起始索引
            int start = sheetIdx * sheetSize ;
            int end = Math.min((sheetIdx+1) * sheetSize, list.size());

            // 3.写入标题行
            int rowNum = writeTitleRow(sheet);

            // 4.写入数据
            writeDataRows(sheet, rowNum, list, start, end);
        }

        return wb;
    }

    private int writeTitleRow(Sheet sheet) {
        int rowNum = 0;
        SwanPoiProperties.CellStyle cellStyle = swanPoiProperties.getTitle();

        Row row = sheet.createRow(rowNum++);

        int columnIdx = 0;
        for (ExcelColumnInfo columnInfo : columnInfos) {
            Cell cell = row.createCell(columnIdx++);
            cell.setCellStyle(toPoiCellStyle( cellStyle));
            cell.setCellValue(columnInfo.getExcelColumn().title());
        }
        return rowNum;
    }

    private <T> void writeDataRows(Sheet sheet, int rowNum, List<T> list, int start, int end) {

        for (int i = start; i <end; i++) {
            T rowData = list.get(i);
            Row row = sheet.createRow(rowNum++);

            int columnIdx = 0;
            for (ExcelColumnInfo columnInfo : this.columnInfos) {
                Cell cell = row.createCell(columnIdx++);
                cell.setCellStyle(toPoiCellStyle(swanPoiProperties.getData()));
                Object fieldValue = ReflectUtil.getFieldValue(rowData, columnInfo.getField());

                this.chain.setValue(cell, fieldValue, columnInfo.getExcelColumn());
            }
        }
    }


    private CellStyle toPoiCellStyle( SwanPoiProperties.CellStyle cellStyle) {

        CellStyle cs = wb.createCellStyle();

        Font font = wb.createFont();
        font.setFontName(cellStyle.getFontName());
        font.setBold(cellStyle.isFontBold());
        font.setFontHeightInPoints(cellStyle.getFontHeight());
        font.setColor(cellStyle.getFontColor().getIndex());
        cs.setFont(font);

        return cs;
    }

}
