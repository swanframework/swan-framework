package com.swan.poi.config;

import lombok.Data;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Data
@ConfigurationProperties(prefix = "swan.poi")
public class SwanPoiProperties {

    /** 标题行样式 */
    private CellStyle title;

    /** 数据行样式 */
    private CellStyle data;

    /** 统计行样式 */
    private CellStyle statistics;

    /** 隔行配置 */
    private InterlaceStyle interlace;

    /** 每个 sheet 最大行数量 */
    private Integer sheetSize = 50;

    @Data
    public static class CellStyle {

        /** 字体名称 */
        private String fontName;

        /** 字体高度 */
        private short fontHeight;

        /** 字体是否加粗 */
        private boolean fontBold;

        /** 字体颜色 */
        private IndexedColors fontColor;

        /** 背景颜色 */
        private IndexedColors backColor;

        /** 水平对齐方式 */
        private HorizontalAlignment horAlign;

        /** 垂直对齐方式 */
        private VerticalAlignment verAlign;

        /** 左边框样式 */
        private BorderStyle leftBorder;

        /** 左边框颜色 */
        private IndexedColors leftBorderColor;

    }

    @Data
    public static class InterlaceStyle {

        /** 奇数行样式 */
        private CellStyle oddRow;

        /** 偶数行样式 */
        private CellStyle evenRow;
    }


}
