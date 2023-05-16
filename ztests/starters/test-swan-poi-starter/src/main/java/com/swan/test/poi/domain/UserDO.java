package com.swan.test.poi.domain;

import com.swan.poi.anno.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zongf
 * @since 2023-05-16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO extends BaseDO {

    @ExcelColumn(title = "用户名")
    private String username;

    @ExcelColumn(title = "备注")
    private String remark;

    @ExcelColumn(title = "创建时间")
    private Date createTime;

    private String password;

    @ExcelColumn(title = "资产", scale = 3, roundingMode = BigDecimal.ROUND_CEILING)
    private BigDecimal money;

    @ExcelColumn(title = "long", scale = 3, roundingMode = BigDecimal.ROUND_CEILING)
    private Double lg;

    @ExcelColumn(title = "布尔", scale = 3, roundingMode = BigDecimal.ROUND_CEILING)
    private boolean bl;



}
