package com.swan.mybatis.condition;

import com.swan.core.exception.SwanBaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/** 查询条件
 * @author zongf
 * @since 2022-11-12
 **/
@Setter @Getter
public class Criterion {

    // 字段名
    private String field;

    // 字段名
    private String column;

    // 操作符
    private String operator;

    // 操作符类型: 0-不需要参数, 1-一个参数, 2-两个参数 3-多个参数
    private Integer operatorParamType;

    // 参数值
    private Object value;

    // between 参数值
    private Object secondValue;

    // list 参数列表
    private List<Object> values;

    // 逻辑运算符: and | or
    private String logicOp;

}
