package com.swan.test.mybatis.mapper.count;

 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.condition.AutoCondition;
import com.swan.test.mybatis.condition.AutoDelCondition;
import com.swan.test.mybatis.condition.AutoDelVersionCondition;
import com.swan.test.mybatis.condition.AutoVersionCondition;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;

import java.util.List;
import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @date 2021-01-10
 */
public class BatchCountOnConditionTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){

        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5, "zhangsan_", "lisi_");

        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);
        AutoCondition condition = new AutoCondition("zhangsan");
        condition.leftLike(AutoEntity.Fields.name, "zhang");

        Integer count = this.autoMapper.countOnCondition(condition);
        Assertions.assertEquals(5, count.intValue());

    }

    @Test
    public void condition(){

        AutoCondition condition = new AutoCondition("zhangsan");

        Integer count = this.autoMapper.countOnCondition(condition);
        Assertions.assertEquals(5, count.intValue());

    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5, "zhangsan_", "lisi_");

        int num = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoDelMapper.countOnCondition(new AutoDelCondition("zhangsan"));
        Assertions.assertEquals(5, count.intValue());

    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5, "zhangsan_", "lisi_");

        int num = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoVersionMapper.countOnCondition(new AutoVersionCondition("lisi"));
        Assertions.assertEquals(5, count.intValue());

    }

    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhangsan_", "lisi_");

        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoDelVersionMapper.countOnCondition(new AutoDelVersionCondition("lisi"));
        Assertions.assertEquals(5, count.intValue());


    }


}
