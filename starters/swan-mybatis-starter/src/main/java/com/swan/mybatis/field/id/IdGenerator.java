package com.swan.mybatis.field.id;

/** 主键生成器
 * @param <T> 主键类型
 * @author zongf
 * @date 2021-01-08
 */
public interface IdGenerator<T> {

    /** 生成主键Id
     * @return
     * @author zongf
     * @date 2021-01-08
     */
    public T generateId();

}
