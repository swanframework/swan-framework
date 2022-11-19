package com.swan.mybatis.mapper.field.id;

/** 数据库主键生成器
 * @author zongf
 * @date 2020-11-27
 */
public class AutoIncIdGenerator implements IdGenerator {

    @Override
    public Long generateId() {
        // 强制返回null, 交由数据库生成. 因为对于mysql, 如果设置了主键id, 那么会使用设置的id, 而非数据库自增的id
        return null;
    }

}