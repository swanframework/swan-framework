package com.swan.mybatis.condition;

import com.swan.core.utils.NameUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author zongf
 * @since 2022-11-12
 **/
@Setter @Getter
@NoArgsConstructor
public class Criterion {

    // 字段名
    private String field;

    // 字段名
    private String column;

    // 操作符
    private String operator;

    // 参数值
    private Object value;

    // between 参数值
    private Object secondValue;

    // list 参数列表
    private List<Object> listValues;

    // 无参运算符
    private boolean noParamOp;

    // 单个参数运算符
    private boolean singleParamOp;

    // 两个参数运算符
    private boolean secondParamOp;

    // list参数运算符
    private boolean listParamOp;

    public void init(String field, String operator) {
        this.field = field;
        this.column = NameUtil.toHungaryName(field);
        this.operator = operator;
    }

    // 无参数
    public Criterion(String field, String operator) {
        init(field, operator);
        this.singleParamOp = true;
    }

    // 单个参数
    public Criterion(String field, String operator, Object value) {
        this(field, operator);
        this.singleParamOp = true;
        this.value = value;
    }

    // 单个参数
    public Criterion(String field, String operator, Object value, Object secondValue) {
        this(field, operator);
        this.secondParamOp = true;
        this.value = value;
        this.secondValue = secondValue;
    }

    // list
    public Criterion(String field, String operator, List<Object> list) {
        this(field, operator);
        this.listParamOp = true;
        this.listValues = list;
    }
}
