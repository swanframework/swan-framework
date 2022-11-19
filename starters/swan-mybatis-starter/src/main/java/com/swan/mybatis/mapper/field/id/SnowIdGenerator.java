package com.swan.mybatis.mapper.field.id;


import com.swan.core.utils.SnowIdUtil;

/** 雪花主键生成器
 * @author zongf
 * @since 2020-11-27
 */
public class SnowIdGenerator implements IdGenerator {

    @Override
    public Long generateId() {
        return SnowIdUtil.generateId();
    }

}
