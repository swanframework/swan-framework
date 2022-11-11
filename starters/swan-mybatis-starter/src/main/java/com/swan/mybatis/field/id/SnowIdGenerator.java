package com.swan.mybatis.field.id;


import com.swan.mybatis.util.SnowIdUtil;

/** 雪花主键生成器
 * @author zongf
 * @date 2020-11-27
 */
public class SnowIdGenerator implements IdGenerator {

    @Override
    public Long generateId() {
        return SnowIdUtil.generateId();
    }

}
