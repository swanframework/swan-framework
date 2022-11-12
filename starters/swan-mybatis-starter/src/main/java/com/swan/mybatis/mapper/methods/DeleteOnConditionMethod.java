package com.swan.mybatis.mapper.methods;

import com.sun.istack.internal.NotNull;
import com.swan.mybatis.condition.Condition;
import org.apache.ibatis.annotations.Param;

public interface DeleteOnConditionMethod<ID, E>  extends BaseMethod {

    /** 根据条件，删除对象 <br/>
     *  删除策略: 是否使用 @Delete 修饰字段 <br/>
     *    * 逻辑删除: 执行 update 操作 <br/>
     *    * 物理删除: 执行 delete 操作<br/>
     *  版本管理: 自行维护版本号
     * @param condition 删除条件，为空时，删除全部数据，严谨调用
     * @return 删除的记录数
     */
    public int deleteOnCondition(@NotNull @Param("condition") Condition condition);

}
