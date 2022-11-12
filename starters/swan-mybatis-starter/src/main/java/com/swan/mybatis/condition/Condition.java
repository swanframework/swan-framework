package com.swan.mybatis.condition;

import com.swan.mybatis.core.ICondition;

import java.util.List;

/** 查询条件
 * @author zongf
 * @date 2021-01-12
 */
public interface Condition extends ICondition {

    public void isNull(String field);
    public void isNotNull(String field);

    public void equal(String field, Object value);
    public void notEqual(String field, Object value);
    public void lessOrEqual(String field, Object value);
    public void greaterThan(String field, Object value);
    public void lessThan(String field, Object value);
    public void greaterOrEqual(String field, Object value);

    public void in(String field, List<Object> value);
    public void notIn(String field, List<Object> value);

    public void between(String field, Object first, Object second);
    public void notBetween(String field,  Object first, Object second);

    public void like(String field, Object value);
    public void leftLike(String field, Object value);
    public void rightLike(String field, Object value);

    public void notLike(String field, Object value);
    public void notLeftLike(String field, Object value);
    public void notRightLike(String field, Object value);

}
