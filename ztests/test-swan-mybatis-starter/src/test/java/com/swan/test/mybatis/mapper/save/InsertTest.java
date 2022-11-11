package com.swan.test.mybatis.mapper.save;

 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;
import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @date 2021-01-10
 */
public class InsertTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        AutoEntity demo = new AutoEntity("zhangsan", 20);
        boolean success = this.autoMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        AutoEntity entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
    }

    @Test
    public void autoDelMapper(){
        AutoDelEntity demo = new AutoDelEntity("zhangsan", 20);
        boolean success = this.autoDelMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getDel());

        AutoDelEntity entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(DEL_NO, entity.getDel());
    }

    @Test
    public void autoVersionMapper(){
        AutoVersionEntity demo = new AutoVersionEntity("zhangsan", 20);
        boolean success = this.autoVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getVersion());

        AutoVersionEntity entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(START_VERSION, entity.getVersion());
    }

    @Test
    public void autoDelVersionMapper(){
        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan", 20);
        boolean success = this.autoDelVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getDel());
        Assertions.assertNull(demo.getVersion());

        AutoDelVersionEntity entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(START_VERSION, entity.getVersion());
        Assertions.assertEquals(DEL_NO, entity.getDel());
    }


}
