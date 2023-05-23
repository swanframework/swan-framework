package com.swan.mybatis.condition;

import com.swan.core.constants.NumberConstant;
import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.core.exception.SwanBaseException;
import com.swan.core.utils.ListUtil;
import com.swan.core.utils.NameUtil;
import lombok.Getter;

import java.util.*;

import static com.swan.mybatis.condition.OpType.*;

/** where 条件类
 * @author zongf
 * @since 2022-11-12
 **/
@Getter
public class Condition {

    // 筛选条件
    private List<Criterion> criterionList;

    private static final String AND = "and";
    private static final String OR = "or";

    private static final  Map<Integer, List<OpType>> opTypeMap;

    private boolean ignoreEmpty;

    static {
        opTypeMap = ListUtil.groupToMap(Arrays.asList(values()), OpType::getParams);
    }

    private Condition(List<Criterion> list) {
        this.criterionList = list;
    }

    public static Condition newInstance() {
        return new Condition(new ArrayList<>());
    }

    public static Condition newInstance(boolean ignoreEmptyString) {
        Condition condition = new Condition(new ArrayList<>());
        condition.ignoreEmptyString();
        return condition;
    }

    /** 清空所有筛选条件
     * @return Condition
     * @since 2022-11-18
     */
    public Condition clear() {
        criterionList.clear();
        return this;
    }

    /** 添加 and 条件, 适用无参数操作符，如 is null, is not null
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符
     * @return Condition
     * @since 2022-11-18
     */
    public Condition and(String field, OpType opType) {
        if (!opTypeMap.get(NumberConstant.ZERO).contains(opType)) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "参数数量不对");
        }
        return addCondition(AND, field, opType, new Object[]{});
    }

    /** 添加 and 条件, 适用单参数操作符，如 >,=,like 等. <br/>
     *  如果参数为 null，则不添加条件
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符
     * @param value 参数
     * @return Condition
     * @since 2022-11-18
     */
    public Condition and(String field, OpType opType, Object value) {
        if (!opTypeMap.get(NumberConstant.ONE).contains(opType)) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "参数数量不对");
        }
        if (isEmpty(value)) {
            return this;
        }
        return addCondition(AND, field, opType, new Object[]{value});
    }

    /** 添加 and 条件，适用 between 操作符
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符,
     * @param min 最小值
     * @param max 最大值
     * @return Condition
     * @since 2022-11-18
     */
    public Condition and(String field, OpType opType, Object min, Object max) {
        if (!opTypeMap.get(NumberConstant.TWO).contains(opType)) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "参数数量不对");
        }
        return addCondition(AND, field, opType, new Object[]{min, max});
    }

    /** 添加 and 条件，适用 between 操作符 <br/>
     *  如果列表为空，则不添加条件
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符,
     * @param values 参数列表
     * @return Condition
     * @since 2022-11-18
     */
    public Condition and(String field, OpType opType, List values) {
        if (!opTypeMap.get(NumberConstant.THREE).contains(opType)) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "参数数量不对");
        }
        if (Objects.isNull(values) || values.isEmpty()) {
            return this;
        }
        return addCondition(AND, field, opType, values.toArray());

    }

    /** 添加 or 条件, 适用无参数操作符，如 is null, is not null
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符
     * @return Condition
     * @since 2022-11-18
     */
    public Condition or(String field, OpType opType) {
        if (!opTypeMap.get(NumberConstant.ZERO).contains(opType)) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "参数数量不对");
        }
        return addCondition(OR, field, opType, new Object[]{});
    }

    /** 添加 or 条件, 适用单参数操作符，如 >,=,like 等
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符
     * @param value 参数
     * @return Condition
     * @since 2022-11-18
     */
    public Condition or(String field, OpType opType, Object value) {
        if (!opTypeMap.get(NumberConstant.ONE).contains(opType)) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "参数数量不对");
        }
        if (isEmpty(value)) {
            return this;
        }
        return addCondition(OR, field, opType, new Object[]{value});
    }

    /** 添加 or 条件，适用 between 操作符
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符,
     * @param min 最小值
     * @param max 最大值
     * @return Condition
     * @since 2022-11-18
     */
    public Condition or(String field, OpType opType, Object min, Object max) {
        if (!opTypeMap.get(NumberConstant.TWO).contains(opType)) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "参数数量不对");
        }
        return addCondition(OR, field, opType, new Object[]{min, max});

    }

    /** 添加 or 条件，适用 between 操作符
     * @param field java实体属性名，自动映射为字段名
     * @param opType 关系操作符,
     * @param values 参数列表
     * @return Condition
     * @since 2022-11-18
     */
    public Condition or(String field, OpType opType, List values) {
        if (!opTypeMap.get(NumberConstant.THREE).contains(opType)) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "参数数量不对");
        }
        if (Objects.isNull(values) || values.isEmpty()) {
            return this;
        }
        return addCondition(OR, field, opType, values.toArray());
    }

    private Condition addCondition(String relationOp, String field, OpType opType, Object[] values) {
        Criterion criterion = CriterionFactory.create(relationOp, NameUtil.toHungaryName(field), opType, values);
        this.criterionList.add(criterion);
        return this;
    }

    public boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }
        if (this.ignoreEmpty && value instanceof String && ((String) value).isEmpty()) {
            return true;
        }
        return false;
    }

    public void ignoreEmptyString() {
        this.ignoreEmpty = true;
    }

}
