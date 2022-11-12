package com.swan.test.mybatis.method.count;


import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author zongf
 * @date 2021-01-10
 */
public class CountTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){

        // 生成5条数据
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);

        // 向数据库中插入一批数据
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        // 预期查询数量和插入数量相同
        Integer count = this.autoMapper.count();
        Assertions.assertEquals(demoList.size(), count);
    }

}
