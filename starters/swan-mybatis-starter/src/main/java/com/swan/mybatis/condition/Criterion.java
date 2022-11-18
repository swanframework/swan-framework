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
@NoArgsConstructor
public class Criterion {

    // 字段名
    private String field;

    // 字段名
    private String column;

    // 操作符
    private String operator;

    // 操作符类型: 0-不需要参数, 1-一个参数, 2-两个参数 3-多个参数
    private Integer operatorParams;

    // 参数值
    private Object value;

    // between 参数值
    private Object secondValue;

    // list 参数列表
    private List<Object> listValues;

    // 逻辑运算符: and | or
    private String logicOp;

    private static Criterion init(String logicOp, String column, String operator) {
        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setOperatorParams(0);
        return criterion;
    }
    private static Criterion init(String logicOp, String column, String operator, Object value) {
        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setValue(value);
        criterion.setOperatorParams(1);
        return criterion;
    }
    private static Criterion init(String logicOp, String column, String operator, Object value, Object extValue) {
        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setValue(value);
        criterion.setSecondValue(extValue);
        criterion.setOperatorParams(2);
        return criterion;
    }
    private static Criterion init(String logicOp, String column, String operator, List<Object> values) {
        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setListValues(values);
        criterion.setOperatorParams(3);
        return criterion;
    }

    public static Criterion build(String logicOp, String column, OpType opType, Object[] values) {

        switch (opType) {
            case isNull:
            case isNotNull:
                // 0 个参数
                if (values.length != 0) {
                    throw new SwanBaseException(opType.getOperator() + "不能包含参数");
                }
                return init(logicOp, column, opType.getOperator());
            case between:
            case notBetween:
                if (values.length != 2) {
                    throw new SwanBaseException(opType.getOperator() + "只能包含两个参数");
                }
                // 两个参数
               return init(logicOp, column, opType.getOperator(), values[0], values[1]);
            case in:
            case notIn:
                // 多个参数
                if (values.length == 0) {
                    throw new SwanBaseException(opType.getOperator() + "至少需要一个参数");
                }
                return init(logicOp, column, opType.getOperator(), Arrays.asList(values));
            case like:
            case notLike:
                // 默认1个参数
                if (values.length != 1) {
                    throw new SwanBaseException(opType.getOperator() + "只能包含一个参数");
                }
                return init(logicOp, column, opType.getOperator(), "%" + values[0] + "%");

            case leftLike:
            case leftNotLike:
                // 默认1个参数
                if (values.length != 1) {
                    throw new SwanBaseException(opType.getOperator() + "只能包含一个参数");
                }
                return init(logicOp, column, opType.getOperator(), values[0] + "%");
            case rightLike:
            case rightNotLike:
                // 默认1个参数
                if (values.length != 1) {
                    throw new SwanBaseException(opType.getOperator() + "只能包含一个参数");
                }
                return init(logicOp, column, opType.getOperator(), "%" + values[0]);
            default:
                // 默认1个参数
                if (values.length != 1) {
                    throw new SwanBaseException(opType.getOperator() + "只能包含一个参数");
                }
                return init(logicOp, column, opType.getOperator(), values[0]);
        }
    }

}
