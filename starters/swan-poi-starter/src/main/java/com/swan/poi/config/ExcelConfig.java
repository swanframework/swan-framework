package com.swan.poi.config;

import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** 全局样式默认配置
 * @author zongf
 * @since 2023-05-16
 **/
@Data
@ConfigurationProperties(prefix = "swan.poi.excel")
public class ExcelConfig {

    /** 每个 sheet 最大行数量 */
    private Integer sheetSize = 50;

    /** 样式配置 */
    private Style style = new Style();

    @Data
    public static class Style{

        /** 标题行样式 */
        private RoleStyle title = new RoleStyle();

        /** 数据行样式 */
        private RoleStyle data = new RoleStyle();

        /** 隔行样式，优先级高于数据行样式配置 */
        private InterlaceStyle interlace = new InterlaceStyle();

    }

    @Data
    public static class InterlaceStyle {

        /** 奇数行样式 */
        private RoleStyle oddRow = new RoleStyle();

        /** 偶数行样式 */
        private RoleStyle evenRow = new RoleStyle();
    }

    @Data
    public static class RoleStyle {

        /** 宽度: 单位为字符 */
        private Integer width = 14;

        /** 高度: 单位为字符 */
        private Integer height = 16;

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
        private HorizontalAlignment horAlign = HorizontalAlignment.CENTER;

        /** 垂直对齐方式 */
        private VerticalAlignment verAlign = VerticalAlignment.CENTER;

    }

}
