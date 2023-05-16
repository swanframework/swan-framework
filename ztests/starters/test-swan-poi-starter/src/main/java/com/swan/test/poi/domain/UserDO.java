package com.swan.test.poi.domain;

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
public class UserDO extends BaseDO {

    @ExcelColumn(title = "主键ID")
    private String username;

    private String password;

}
