package com.swan.mybatis.condition;

/** 关系运算符
 * @author zongf
 * @since 2022-11-18
 */
public enum OpType {

    equals("="),
    notEquals("!="),

    lessThan("<"),
    lessOrEquals("<="),

    greaterThan(">"),
    greaterOrEquals(">="),

    /** 左右闭区间 */
    between("between"),
    /** 左右开区间 */
    notBetween("not between"),

    in("in"),
    notIn("not in"),

    like("like"),
    notLike("not like"),

    leftLike("like"),
    leftNotLike("not like"),

    rightLike("like"),
    rightNotLike("not like"),

    isNull("is null"),
    isNotNull("is not null"),
    ;

    private String operator;

    OpType(String operator){
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }

}
