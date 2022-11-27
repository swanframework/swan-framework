package com.swan.mybatis.condition;

import com.swan.core.exception.SwanBaseException;

import java.util.Arrays;
import java.util.List;

/**
 * @author zongf
 * @since 2022-11-19
 **/
public class CriterionFactory {


    private static Criterion createNoParam(String logicOp, String column, String operator, Object[] values) {
        // 0 个参数
        if (values.length != 0) {
            throw new SwanBaseException(operator + "不能包含参数");
        }

        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setOperatorParamType(0);
        return criterion;
    }
    private static Criterion createOneParam(String logicOp, String column, String operator, Object value) {
        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setValue(value);
        criterion.setOperatorParamType(1);
        return criterion;
    }

    private static Criterion createTwoParams(String logicOp, String column, String operator, Object[] values) {
        if (values.length != 2) {
            throw new SwanBaseException(operator + "只能包含两个参数");
        }
        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setValue(values[0]);
        criterion.setSecondValue(values[1]);
        criterion.setOperatorParamType(2);
        return criterion;
    }

    private static Criterion createMultiParams(String logicOp, String column, String operator, List<Object> values) {
        // 多个参数
        if (values.size() == 0) {
            throw new SwanBaseException(operator + "至少需要一个参数");
        }
        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setValues(values);
        criterion.setOperatorParamType(3);
        return criterion;
    }

    private static void checkOneParam(Object[] values, OpType opType) {
        // 默认1个参数
        if (values.length != 1) {
            throw new SwanBaseException(opType.getOperator() + "只能包含一个参数");
        }
    }

    public static Criterion create(String logicOp, String column, OpType opType, Object[] values) {
        switch (opType) {
            case isNull:
            case isNotNull:
                // 无参数
                return createNoParam(logicOp, column, opType.getOperator(), values);
            case between:
            case notBetween:
                if (values.length == 0) {
                    return null;
                }
                // 两个参数
                return createTwoParams(logicOp, column, opType.getOperator(), values);
            case in:
            case notIn:
                if (values.length == 0) {
                    return null;
                }
                // 多个参数
                return createMultiParams(logicOp, column, opType.getOperator(), Arrays.asList(values));
            case like:
            case notLike:
                if (values.length == 0) {
                    return null;
                }
                checkOneParam(values, opType);
                return createOneParam(logicOp, column, opType.getOperator(), "%" + values[0] + "%");
            case leftLike:
            case leftNotLike:
                if (values.length == 0) {
                    return null;
                }
                // 默认1个参数
                checkOneParam(values, opType);
                return createOneParam(logicOp, column, opType.getOperator(), values[0] + "%");
            case rightLike:
            case rightNotLike:
                if (values.length == 0) {
                    return null;
                }
                // 默认1个参数
                checkOneParam(values, opType);
                return createOneParam(logicOp, column, opType.getOperator(), "%" + values[0]);
            default:
                if (values.length == 0) {
                    return null;
                }
                // 默认1个参数
                checkOneParam(values, opType);
                return createOneParam(logicOp, column, opType.getOperator(), values[0]);
        }
    }

}
