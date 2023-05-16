package com.swan.poi.anno;

import com.swan.poi.enums.ColumnType;
import com.swan.poi.handler.ExcelCellHandler;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumn {

    /** 列标题 */
    public String title() default "";

    /** 列排序 */
    public int sort() default 0;

    /** 日期类型: 日期格式 */
    public String dateFormat() default "";

    /** BigDecimal类型: 精度 */
    public int scale() default -1;

    /** BigDecimal: 舍入规则 */
    public int roundingMode() default BigDecimal.ROUND_HALF_EVEN;

    /** 当值为空时,字段的默认值    */
    public String defaultValue() default "";

    /** 提示信息  */
    public String prompt() default "";

    /** 自定义字段处理器bean名称, 需要实现 ExcelCellHandler*/
    public String handlerBeanName() default "";

    /** 设置只能选择不能输入的列内容.    */
    public String[] options() default {};

    /** 文字后缀,如% 90 变成90%    */
    public String suffix() default "";

    /** 是否导出数据 */
    public boolean exportData() default true;

    /** 是否自动统计数据,在最后追加一行统计数据总和    */
    public boolean isStatistics() default false;

    /** 导出时在excel中每个列的高度 单位为字符    */
    public double height() default 14;

    /** 导出时在excel中每个列的宽 单位为字符    */
    public double width() default 16;

    /** 水平对齐方式 */
    public HorizontalAlignment horAlign() default HorizontalAlignment.CENTER;

    /** 垂直对齐方式 */
    public VerticalAlignment verAlign() default VerticalAlignment.CENTER;


}
