package com.swan.generator.persistence.po;

import com.swan.generator.enums.MysqlDataType;
import lombok.Getter;
import lombok.Setter;

/** 表字段信息
 * @author zongf
 * @date 2019-11-30
 */
@Setter @Getter
public class ColumnPO {

    // 字段名
    private String columnName;

    // 创建表时, 声明的类型
    private String colunmType;

    // 数据类型
    private MysqlDataType dataType;
    
    // 列索引
    private int position;

    // 字符串最大个数,不区分中英文
    private long maxCharLength;

    // 整数最大位数
    private int maxIntDigits;

    // 小数最大位数
    private int maxDecimalDigits;

    // 是否可为空
    private boolean isNullAble;

    // 是否是主键列, 不代表是唯一主键, 可能是联合主键
    private boolean isPKColumn;
    
    // 是否自增
    private boolean isAutoIncrement;
    
    // 列注释
    private String comment;
    
    // 默认值
    private String defaultValue;

}
