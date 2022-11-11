package com.swan.test.mybatis.mapper.count;

 
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
public class BatchCountTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){

        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);

        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoMapper.count();
        Assertions.assertEquals(demoList.size(), count.intValue());

        boolean delete = this.autoMapper.delete(demoList.get(0));
        Assertions.assertTrue(delete);

        Integer count1 = this.autoMapper.count();
        Assertions.assertEquals(demoList.size() -1, count1.intValue());
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5);

        int num = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoDelMapper.count();
        Assertions.assertEquals(demoList.size(), count.intValue());

        boolean delete = this.autoDelMapper.delete(demoList.get(0));
        Assertions.assertTrue(delete);

        Integer count1 = this.autoDelMapper.count();
        Assertions.assertEquals(demoList.size() -1, count1.intValue());
    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5);

        int num = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoVersionMapper.count();
        Assertions.assertEquals(demoList.size(), count.intValue());

        boolean delete = this.autoVersionMapper.deleteById(demoList.get(0).getId());
        Assertions.assertTrue(delete);

        Integer count1 = this.autoVersionMapper.count();
        Assertions.assertEquals(demoList.size() -1, count1.intValue());
    }

    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5);

        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        Integer count = this.autoDelVersionMapper.count();
        Assertions.assertEquals(demoList.size(), count.intValue());

        boolean delete = this.autoDelVersionMapper.deleteById(demoList.get(0).getId());
        Assertions.assertTrue(delete);

        Integer count1 = this.autoDelVersionMapper.count();
        Assertions.assertEquals(demoList.size() -1, count1.intValue());
    }


}
