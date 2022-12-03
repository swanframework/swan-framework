package com.swan.mybatis.condition;

/** 关系运算符
 * @author zongf
 * @since 2022-11-18
 */
public enum OpType {

    isNull("is null", 0),
    isNotNull("is not null", 0),

    equals("=", 1),
    notEquals("!=", 1),

    lessThan("<", 1),
    lessOrEquals("<=", 1),

    greaterThan(">", 1),
    greaterOrEquals(">=", 1),

    like("like", 1),
    notLike("not like", 1),

    leftLike("like", 1),
    leftNotLike("not like", 1),

    rightLike("like", 1),
    rightNotLike("not like", 1),

    /** 左右闭区间 */
    between("between", 2),
    /** 左右开区间 */
    notBetween("not between", 2),

    in("in", 3),
    notIn("not in", 3),

    ;

    private String operator;

    private Integer params;

    OpType(String operator, Integer params){
        this.operator = operator;
        this.params = params;
    }

    public String getOperator() {
        return this.operator;
    }

    public Integer getParams() {
        return params;
    }
}
