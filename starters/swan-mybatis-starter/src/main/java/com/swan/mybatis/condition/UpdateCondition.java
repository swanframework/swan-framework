package com.swan.mybatis.condition;

import com.swan.core.utils.NameUtil;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zongf
 * @since 2022-11-12
 **/
@Getter
public class UpdateCondition implements Condition{

    private List<Criterion> whereList = new ArrayList<>();

    @Override
    public void isNull(String field) {
        whereList.add(new Criterion(field, "is null"));
    }

    @Override
    public void isNotNull(String field) {
        whereList.add(new Criterion(field, "is not null"));
    }

    @Override
    public void equal(String field, Object value) {
        whereList.add(new Criterion(field, "=", value));
    }

    @Override
    public void notEqual(String field, Object value) {
        whereList.add(new Criterion(field, "!=", value));
    }

    @Override
    public void lessOrEqual(String field, Object value) {
        whereList.add(new Criterion(field, "<=", value));
    }

    @Override
    public void greaterThan(String field, Object value) {
        whereList.add(new Criterion(field, ">=", value));
    }

    @Override
    public void lessThan(String field, Object value) {
        whereList.add(new Criterion(field, "<", value));
    }

    @Override
    public void greaterOrEqual(String field, Object value) {
        whereList.add(new Criterion(field, ">=", value));
    }

    @Override
    public void in(String field, List<Object> list) {
        whereList.add(new Criterion(field, "in", list));
    }

    @Override
    public void notIn(String field, List<Object> list) {
        whereList.add(new Criterion(field, "not in", list));
    }

    @Override
    public void between(String field,  Object first, Object second) {
        whereList.add(new Criterion(field, "between", first, second));
    }

    @Override
    public void notBetween(String field,  Object first, Object second) {
        whereList.add(new Criterion(field, "not between", first, second));
    }

    @Override
    public void like(String field, Object value) {
        whereList.add(new Criterion(field, "like", "%" + value + "%"));
    }

    @Override
    public void leftLike(String field, Object value) {
        whereList.add(new Criterion(field, "like", value + "%"));
    }

    @Override
    public void rightLike(String field, Object value) {
        whereList.add(new Criterion(field, "like", "%" + value));

    }

    @Override
    public void notLike(String field, Object value) {
        whereList.add(new Criterion(field, "like", "%" + value + "%"));
    }

    @Override
    public void notLeftLike(String field, Object value) {
        whereList.add(new Criterion(field, "not like", value + "%"));
    }

    @Override
    public void notRightLike(String field, Object value) {
        whereList.add(new Criterion(field, "not like", "%" + value));
    }

}
