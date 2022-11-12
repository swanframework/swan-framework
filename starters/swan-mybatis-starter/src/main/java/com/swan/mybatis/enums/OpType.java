package com.swan.mybatis.enums;

public enum OpType {


    eq("="),
    le("<="),
    ge(">="),
    lt("<"),
    gt("="),

    in("in"),
    between("between"),
    like("like"),

    notIn("not in"),
    notBetween("not between"),
    notLike("not like"),

    groupBy("group by"),
    having("having"),

    isNull("is null"),
    notNull("is not null")

    ;

    private String operator;
    OpType(String operator){
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }

}
