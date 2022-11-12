package com.swan.test.mybatis.method.count;


import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.UpdateCondition;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.condition.AutoCondition;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.po.AutoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author zongf
 * @date 2021-01-10
 */
public class CountOnConditionTest extends BaseAutoMapperTest {

    @Test
    public void leftLike() {

        // 生成5条数据
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5, "zhang");

        // 向数据库中插入一批数据
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        // 预期查询数量和插入数量相同
        AutoCondition condition = new AutoCondition();
        condition.leftLike(AutoEntity.Fields.name, "zhang");
        Integer all = this.autoMapper.countOnCondition(condition);
        Assertions.assertEquals(demoList.size(), all);

        // 查询预期为0
        condition.leftLike(AutoEntity.Fields.name, "li");
        Integer zero = this.autoMapper.countOnCondition(condition);
        Assertions.assertEquals(0, zero);
    }


    @Test
    public void rightLike() {

        // 生成5条数据
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5, "zong");

        // 向数据库中插入一批数据
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        // 预期查询数量和插入数量相同
        AutoCondition condition = new AutoCondition();
        condition.rightLike(AutoEntity.Fields.name, "1");
        Integer one = this.autoMapper.count();
        Assertions.assertEquals(1, one);

        condition.rightLike(AutoEntity.Fields.name, "10");
        Integer zero = this.autoMapper.countOnCondition(condition);
        Assertions.assertEquals(0, zero);
    }


}
