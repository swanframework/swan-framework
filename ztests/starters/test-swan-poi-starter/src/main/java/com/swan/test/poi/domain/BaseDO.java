package com.swan.test.poi.domain;

import cn.hutool.core.util.RandomUtil;
import com.swan.poi.anno.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDO {


    @ExcelColumn(title = "主键ID")
    private Integer id ;


}
