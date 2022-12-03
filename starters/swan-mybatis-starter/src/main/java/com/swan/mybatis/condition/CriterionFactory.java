package com.swan.mybatis.condition;


import java.util.Arrays;
import java.util.List;

/**
 * @author zongf
 * @since 2022-11-19
 **/
public class CriterionFactory {

    public static Criterion create(String logicOp, String column, OpType opType, Object[] values) {
        switch (opType) {
            case isNull:
            case isNotNull:
                // 无参数
                return createNoParam(logicOp, column, opType.getOperator(), values);
            case between:
            case notBetween:
                // 两个参数
                return createTwoParams(logicOp, column, opType.getOperator(), values);
            case in:
            case notIn:
                // 多个参数
                return createMultiParams(logicOp, column, opType.getOperator(), Arrays.asList(values));
            case like:
            case notLike:
                return createOneParam(logicOp, column, opType.getOperator(), "%" + values[0] + "%");
            case leftLike:
            case leftNotLike:
                return createOneParam(logicOp, column, opType.getOperator(), values[0] + "%");
            case rightLike:
            case rightNotLike:
                return createOneParam(logicOp, column, opType.getOperator(), "%" + values[0]);
            default:
                return createOneParam(logicOp, column, opType.getOperator(), values[0]);
        }
    }

    private static Criterion createNoParam(String logicOp, String column, String operator, Object[] values) {
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
        Criterion criterion = new Criterion();
        criterion.setLogicOp(logicOp);
        criterion.setColumn(column);
        criterion.setOperator(operator);
        criterion.setValues(values);
        criterion.setOperatorParamType(3);
        return criterion;
    }

}
