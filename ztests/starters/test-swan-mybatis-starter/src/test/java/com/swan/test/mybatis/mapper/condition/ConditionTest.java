package com.swan.test.mybatis.mapper.condition;


import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.OpType;
import com.swan.test.mybatis.BaseMapperTest;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.po.AutoEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

/** 测试自定义 mapper.xml 中使用内置 condition
 * @author zongf
 * @since 2021-01-10
 */
public class ConditionTest extends BaseMapperTest {

    @Test
    public void none_param() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);


        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.name, OpType.leftLike, "zhang")
                .and(AutoEntity.Fields.id, OpType.greaterOrEquals, 0)
                .and(AutoEntity.Fields.age, OpType.greaterOrEquals, 0)
                .and(AutoEntity.Fields.name, OpType.isNotNull);
        List<AutoEntity> autoEntities = this.autoMapper.queryOnCondition(condition);

        System.out.println(autoEntities.size());

    }




}
