package com.swan.mybatis.mapper.field.id;

import java.util.UUID;

/** UUID 主键生成器
 * @author zongf
 * @date 2021-01-08
 */
public class UUIdGenerator implements IdGenerator<String> {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
