package com.swan.poi.utils;

import com.swan.poi.config.ExcelConfig;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Objects;

/**
 * @author zongf
 * @since 2023-05-18
 **/
public class CellStyleUtil {

    // 设置单元格样式
    public static CellStyle converterStyle(Workbook wb, ExcelConfig.RoleStyle roleStyle) {

        CellStyle cs = wb.createCellStyle();

        // 设置字体样式
        Font font = wb.createFont();
        font.setBold(roleStyle.isFontBold());
        font.setFontName(roleStyle.getFontName());
        if (Objects.nonNull(roleStyle.getFontHeight())) {
            font.setFontHeightInPoints(roleStyle.getFontHeight());
        }
        if (Objects.nonNull(roleStyle.getFontColor())) {
            font.setColor(roleStyle.getFontColor().getIndex());
        }
        cs.setFont(font);

        // 设置背景色
        if (Objects.nonNull(roleStyle.getBackColor())) {
            cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cs.setFillForegroundColor(roleStyle.getBackColor().getIndex());
        }

        // 设置对齐方式
        if (Objects.nonNull(roleStyle.getVerAlign())) {
            cs.setVerticalAlignment(roleStyle.getVerAlign());
        }
        if (Objects.nonNull(roleStyle.getHorAlign())) {
            cs.setAlignment(roleStyle.getHorAlign());
        }

        // 处理边框样式
        if (Objects.nonNull(roleStyle.getBorderStyle())) {
            cs.setBorderLeft(roleStyle.getBorderStyle());
            cs.setBorderRight(roleStyle.getBorderStyle());
            cs.setBorderTop(roleStyle.getBorderStyle());
            cs.setBorderBottom(roleStyle.getBorderStyle());
        }

        // 处理边框颜色
        if (Objects.nonNull(roleStyle.getBorderColor())) {
            cs.setLeftBorderColor(roleStyle.getBorderColor().getIndex());
            cs.setRightBorderColor(roleStyle.getBorderColor().getIndex());
            cs.setTopBorderColor(roleStyle.getBorderColor().getIndex());
            cs.setBottomBorderColor(roleStyle.getBorderColor().getIndex());
        }

        return cs;
    }
}
