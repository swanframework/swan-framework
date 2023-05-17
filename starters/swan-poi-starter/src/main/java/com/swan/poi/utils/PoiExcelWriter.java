package com.swan.poi.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.swan.core.utils.ReflectUtil;
import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.cache.ExcelColumnCache;
import com.swan.poi.config.SwanPoiProperties;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.handler.IExcelCellHandler;
import com.swan.poi.handler.ExcelCellHandlerChain;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

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

    // 事件处理器
    private ExcelCellHandlerChain chain;

    public PoiExcelWriter(Class clz, String sheetName
            , SwanPoiProperties swanPoiProperties, ExcelCellHandlerChain chain) {
        this.sheetName = sheetName;
        this.swanPoiProperties = swanPoiProperties;
        this.chain = chain;

        this.wb = new SXSSFWorkbook(rowAccessWindowSize);

        this.columnInfos = ExcelColumnCache.get(clz);
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
        SwanPoiProperties.RoleStyle titleRowStyle = swanPoiProperties.getStyle().getTitle();

        // 创建标题行，设置行高
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        row.setHeightInPoints(titleRowStyle.getHeight());

        // 写入标题行
        int columnIdx = 0;
        for (ExcelColumnInfo columnInfo : columnInfos) {
            Cell cell = row.createCell(columnIdx++);
            // 设置标题行样式
            cell.setCellStyle(getCellStyle(titleRowStyle));
            // 设置标题行文本
            cell.setCellValue(columnInfo.getExcelColumn().title());
        }
        return rowNum;
    }

    private <T> void writeDataRows(Sheet sheet, int rowNum, List<T> list, int start, int end) {

        SwanPoiProperties.RoleStyle dataRowStyle = swanPoiProperties.getStyle().getData();

        for (int i = start; i <end; i++) {

            // 创建数据化，并设置行高
            T rowData = list.get(i);
            Row row = sheet.createRow(rowNum++);
            row.setHeightInPoints(dataRowStyle.getHeight());

            int columnIdx = 0;
            for (ExcelColumnInfo columnInfo : this.columnInfos) {
                Cell cell = row.createCell(columnIdx++);
                cell.setCellStyle(getCellStyle(dataRowStyle));
                setCellValue(cell, rowData, columnInfo);
            }
        }
    }

    private <T> void setCellValue( Cell cell, T rowData,ExcelColumnInfo columnInfo) {
        // 获取字段值
        Object fieldValue = ReflectUtil.getFieldValue(rowData, columnInfo.getField());

        boolean setSuccess = false;
        ExcelColumn excelColumn = columnInfo.getExcelColumn();

        // 如果自定义了字段转换器，则尝试用自定义转换器处理
        if (StringUtils.hasText(excelColumn.handler())) {
            // 自定义了字段解析器
            IExcelCellHandler customerHandler = SpringUtil.getBean(excelColumn.handler(), IExcelCellHandler.class);
            if (Objects.nonNull(customerHandler)) {
                setSuccess = customerHandler.setValue(cell, fieldValue, columnInfo.getExcelColumn());
            }
        }

        // 未成功设置，则使用默认设置
        if (!setSuccess) {
            this.chain.setValue(cell, fieldValue, columnInfo.getExcelColumn());
        }
    }


    // 设置单元格样式
    private CellStyle getCellStyle(SwanPoiProperties.RoleStyle roleStyle) {

        CellStyle cs = wb.createCellStyle();

        // 设置字体样式
        Font font = wb.createFont();
        font.setFontName(roleStyle.getFontName());
        font.setBold(roleStyle.isFontBold());
        font.setFontHeightInPoints(roleStyle.getFontHeight());
        font.setColor(roleStyle.getFontColor().getIndex());
        cs.setFont(font);

        // 设置背景色、前景色
        cs.setFillForegroundColor(roleStyle.getFontColor().getIndex());
        cs.setFillBackgroundColor(roleStyle.getBackColor().getIndex());

        // 设置对齐方式
        cs.setVerticalAlignment(roleStyle.getVerAlign());
        cs.setAlignment(roleStyle.getHorAlign());

        return cs;
    }

}
