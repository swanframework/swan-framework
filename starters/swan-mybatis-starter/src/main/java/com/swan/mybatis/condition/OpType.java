package com.swan.mybatis.condition;

public enum OpType {

    equals("="),
    notEquals("!="),
    lessThan("<"),
    lessOrEquals("<="),
    greaterThan(">"),
    greaterOrEquals(">="),


    in("in"),
    between("between"),
    like("like"),
    leftLike("like"),
    rightLike("like"),

    notIn("not in"),
    notBetween("not between"),
    notLike("not like"),
    leftNotLike("not like"),
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
