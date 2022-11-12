package com.swan.mybatis.mapper.methods;

import java.util.Collection;

public interface InsertListMethod<ID, E>  extends BaseMethod {

    /** 批量插入记录，使用全部字段 <br/>
     * @param entities 自动回写主键id
     * @return 成功保存的记录数
     */
    public int insertList(Collection<E> entities);

}
