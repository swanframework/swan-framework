package com.swan.test.mybatis.mapper.count;

 
import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.OpType;
import org.junit.jupiter.api.Test;
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
public class CountTest extends BaseAutoMapperTest {

    @Test
    public void name() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(3, "zhang");
        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(5, "li");
        this.autoMapper.insertList(zhangList);
        this.autoMapper.insertList(liList);

        int count = this.autoMapper.count(null);
        Assertions.assertEquals(zhangList.size() + liList.size(), count);

        // 查询全部
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.name, OpType.leftLike, "zhang")
                .or(AutoEntity.Fields.name, OpType.leftLike, "li");

        int totalCount = this.autoMapper.count(condition);
        Assertions.assertEquals(zhangList.size() + liList.size(), totalCount);


        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoEntity.Fields.name, OpType.leftLike, "zhang");
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(zhangList.size() , zhangCount);

        // 只查询 li_xxx
        condition.clear()
                .and(AutoEntity.Fields.name, OpType.leftLike, "li");
        int liCount = this.autoMapper.count(condition);
        Assertions.assertEquals(liList.size() , liCount);




    }

    @Test
    public void autoMapper(){

        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);

        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoMapper.count(null);
        Assertions.assertEquals(demoList.size(), count.intValue());

        boolean delete = this.autoMapper.delete(demoList.get(0));
        Assertions.assertTrue(delete);

        Integer count1 = this.autoMapper.count(null);
        Assertions.assertEquals(demoList.size() -1, count1.intValue());
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5);

        int num = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoDelMapper.count(null);
        Assertions.assertEquals(demoList.size(), count.intValue());

        boolean delete = this.autoDelMapper.delete(demoList.get(0));
        Assertions.assertTrue(delete);

        Integer count1 = this.autoDelMapper.count(null);
        Assertions.assertEquals(demoList.size() -1, count1.intValue());
    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5);

        int num = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoVersionMapper.count(null);
        Assertions.assertEquals(demoList.size(), count.intValue());

        boolean delete = this.autoVersionMapper.deleteById(demoList.get(0).getId());
        Assertions.assertTrue(delete);

        Integer count1 = this.autoVersionMapper.count(null);
        Assertions.assertEquals(demoList.size() -1, count1.intValue());
    }

    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5);

        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoDelVersionMapper.count(null);
        Assertions.assertEquals(demoList.size(), count.intValue());

        boolean delete = this.autoDelVersionMapper.deleteById(demoList.get(0).getId());
        Assertions.assertTrue(delete);

        Integer count1 = this.autoDelVersionMapper.count(null);
        Assertions.assertEquals(demoList.size() -1, count1.intValue());
    }


}
