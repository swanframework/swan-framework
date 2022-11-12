package com.swan.mybatis.mapper.methods;


import com.swan.mybatis.condition.Condition;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UpdateOnConditionMethod<ID, E>  extends BaseMethod {

    /** 根据条件批量记录，使用非 null 的字段 <br/>
     * @param fieldValueMap java 属性名和更新值
     * @param condition 更新条件
     * @return 是否成功更新记录
     */
    public boolean updateOnCondition(@Param("fieldValueMap") Map<String, Object> fieldValueMap,
                                            @Param("condition") Condition condition);

}
