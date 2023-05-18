package com.swan.poi.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.core.utils.ReflectUtil;
import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.cache.ExcelColumnCache;
import com.swan.poi.config.ExcelConfig;
import com.swan.poi.domain.ExcelColumnInfo;
import com.swan.poi.exception.SwanPoiException;
import com.swan.poi.handler.CellHandlerChain;
import com.swan.poi.handler.ICellHandler;
import com.swan.poi.service.IExcelTemplate;
import com.swan.poi.utils.CellStyleUtil;
import com.swan.poi.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Slf4j
public class ExcelTemplate implements IExcelTemplate {

    @Autowired
    private ExcelConfig excelConfig;

    @Autowired
    private CellHandlerChain chain;

    private int rowAccessWindowSize = 500;

    @Override
    public <T> Workbook exportData(String sheetName, List<T> list, Class<T> clz, Integer sheetSize) {

        List<ExcelColumnInfo> columnInfos = ExcelColumnCache.getColumns(clz);

        // 校验是否存在 excel 注解
        if (!ExcelColumnCache.hasExcelAnnoField(clz)) {
            String message = String.format("%s 中未使用 %s 定义列信息", clz.getSimpleName(), ExcelColumn.class);
            throw new SwanPoiException(ExceptionCodeEnum.POI.code(), message);
        }


        Workbook wb = new SXSSFWorkbook(rowAccessWindowSize);

        // 计算 sheet 页数
        int sheetNums = Math.max(1, (int) Math.ceil(list.size() * 1.0 / sheetSize));

        for (int sheetIdx = 0; sheetIdx < sheetNums; sheetIdx++) {
            // 1.创建sheet
            Sheet sheet = wb.createSheet();
            wb.setSheetName(sheetIdx, sheetIdx > 0 ? String.format("%s_%s", sheetName, sheetIdx) :sheetName);

            // 2.计算起始索引
            int start = sheetIdx * sheetSize ;
            int end = Math.min((sheetIdx+1) * sheetSize, list.size());

            // 3.写入标题行
            int rowNum = writeTitleRow(wb, sheet, columnInfos, excelConfig.getStyle().getTitle());

            // 4.写入数据
            writeDataRows(wb, sheet, columnInfos, rowNum, list, start, end, excelConfig.getStyle().getData());
        }

        return wb;
    }

    @Override
    public <T> List<T> importData(InputStream is, Class<T> clz, Integer startRow) {

        // 校验是否存在 excel 注解
        if (!ExcelColumnCache.hasExcelAnnoField(clz)) {
            String message = String.format("%s 中未使用 %s 定义列信息", clz.getSimpleName(), ExcelColumn.class);
            throw new SwanPoiException(ExceptionCodeEnum.POI.code(), message);
        }

        // 创建 workBook
        Workbook wb = ExcelUtil.createWorkBook(is);

        List<T> dataList = new ArrayList<>();
        for (int sheetIdx = 0; sheetIdx < wb.getNumberOfSheets(); sheetIdx++) {
            // 获取sheet 页
            Sheet sheet = wb.getSheetAt(sheetIdx);

            // 读取 sheet 数据
            List<T> subList = ExcelUtil.readSheet(sheet, clz, startRow);

            if (!subList.isEmpty()) {
                dataList.addAll(subList);
            }
        }

        return dataList;
    }


    private int writeTitleRow(Workbook wb, Sheet sheet, List<ExcelColumnInfo> columnInfos, ExcelConfig.RoleStyle titleRowStyle) {
        // 创建标题行，设置行高
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        row.setHeightInPoints(titleRowStyle.getHeight());

        // 写入标题行
        int columnIdx = 0;
        for (ExcelColumnInfo columnInfo : columnInfos) {
            Cell cell = row.createCell(columnIdx++);
            // 设置标题行样式
            cell.setCellStyle(CellStyleUtil.converterStyle(wb, titleRowStyle));
            // 设置标题行文本
            cell.setCellValue(columnInfo.getExcelColumn().title());
        }
        return rowNum;
    }

    private <T> void writeDataRows(Workbook wb, Sheet sheet, List<ExcelColumnInfo> columnInfos,
                                   int startRowNum, List<T> list,  int start, int end,
                                   ExcelConfig.RoleStyle dataRowStyle) {
        for (int i = start; i <end; i++) {

            T rowData = list.get(i);

            // 创建数据行，并设置行高
            Row row = sheet.createRow(startRowNum++);
            row.setHeightInPoints(dataRowStyle.getHeight());

            int columnIdx = 0;
            for (ExcelColumnInfo columnInfo : columnInfos) {
                int width = (int) (dataRowStyle.getWidth() + 0.75) * 256;
                sheet.setColumnWidth(columnIdx, width);

                Cell cell = row.createCell(columnIdx++);

                // 设置样式
                cell.setCellStyle(CellStyleUtil.converterStyle(wb, dataRowStyle));

                // 设置单元格值
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
            ICellHandler customerHandler = SpringUtil.getBean(excelColumn.handler(), ICellHandler.class);
            if (Objects.nonNull(customerHandler)) {
                setSuccess = customerHandler.setValue(cell, fieldValue, columnInfo.getExcelColumn());
            }
        }

        // 未成功设置，则使用默认设置
        if (!setSuccess) {
            this.chain.setValue(cell, fieldValue, columnInfo.getExcelColumn());
        }
    }


}
