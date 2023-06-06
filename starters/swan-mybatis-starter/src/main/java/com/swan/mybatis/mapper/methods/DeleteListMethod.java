package com.swan.mybatis.mapper.methods;

import com.swan.mybatis.condition.Condition;
import org.apache.ibatis.annotations.Param;

public interface DeleteListMethod<ID, E>  extends BaseMethod {

    /** 按条件批量删除 <br/>
     *  删除策略: 是否使用 @Delete 修饰字段 <br/>
     *    * 逻辑删除: 执行 update 操作, 按 id 更新数据 <br/>
     *    * 物理删除: 执行 delete 操作, 按 id 删除数据 <br/>
     * @param condition condition 不能为空
     * @return 是否成功删除一条数据
     */
    public int deleteList(@Param("condition")Condition condition);

}
