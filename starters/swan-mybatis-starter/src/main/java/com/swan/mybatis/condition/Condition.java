package com.swan.mybatis.condition;

import com.swan.core.utils.NameUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/** where 条件类
 * @author zongf
 * @since 2022-11-12
 **/
@Getter
public class Condition {

    private List<Criterion> criterionList;

    private Condition(List<Criterion> list) {
        this.criterionList = list;
    }

    public static Condition newInstance() {
        return new Condition(new ArrayList<>());
    }

    public Condition and(String field, OpType opType, Object... values) {
        this.criterionList.add(Criterion.build("and", NameUtil.toHungaryName(field), opType, values));
        return this;
    }

    public Condition or(String field, OpType opType, Object... values) {
        this.criterionList.add(Criterion.build("or", NameUtil.toHungaryName(field), opType, values));
        return this;
    }

    public Condition clear() {
        criterionList.clear();
        return this;
    }


}
