package com.swan.poi.domain;

import com.swan.poi.anno.ExcelColumn;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Data
public class ExcelColumnInfo {

    /** 注解信息 */
    private ExcelColumn excelColumn;

    /** 反射字段 */
    private Field field;

}
