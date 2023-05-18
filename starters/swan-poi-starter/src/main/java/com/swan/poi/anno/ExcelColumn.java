package com.swan.poi.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumn {

    /** 列排序 */
    public int sort() default 0;

    /** 列标题 */
    public String title() default "";

    /** 日期类型: 日期格式 */
    public String dateFormat() default "";

    /** BigDecimal、double 类型: 精度 */
    public int scale() default -1;

    /** BigDecimal、double 类型: 舍入规则 */
    public int roundingMode() default BigDecimal.ROUND_HALF_EVEN;

    /** 自定义字段处理器bean名称, 需要实现 ExcelCellHandler*/
    public String handler() default "";

    /** 字段约束信息 */
    public String desc() default "";

}
