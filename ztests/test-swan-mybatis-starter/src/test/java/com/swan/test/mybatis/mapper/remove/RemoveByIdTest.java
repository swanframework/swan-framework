package com.swan.test.mybatis.mapper.remove;

 
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
public class RemoveByIdTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        AutoEntity demo = new AutoEntity("zhangsan", 20);
        boolean success = this.autoMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        // 对于无@Delete 字段, removeById 返回成功, 但并未生效
        boolean remove = this.autoMapper.removeById(demo.getId());
        Assertions.assertTrue(remove);

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

        boolean remove = this.autoDelMapper.removeById(demo.getId());
        Assertions.assertTrue(remove);

        // 对于有@Delete 字段, removeById 后 selectById 查询不出来, 但是 selectById 可以查询出来
        Assertions.assertNull(this.autoDelMapper.selectById(demo.getId()));

    }

    @Test
    public void autoVersionMapper(){
        AutoVersionEntity demo = new AutoVersionEntity("zhangsan", 20);
        boolean success = this.autoVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getVersion());

        // 对于无@Delete 字段, removeById 返回成功, 但并未生效. 但是version + 1
        boolean remove = this.autoVersionMapper.removeById(demo.getId());
        Assertions.assertTrue(remove);

        AutoVersionEntity entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(START_VERSION + 1, entity.getVersion().intValue());
    }

    @Test
    public void autoDelVersionMapper(){
        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan", 20);
        boolean success = this.autoDelVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getDel());
        Assertions.assertNull(demo.getVersion());

        boolean remove = this.autoDelVersionMapper.removeById(demo.getId());
        Assertions.assertTrue(remove);

        // 对于有@Delete 字段, removeById 后 selectById 查询不出来, 但是 selectById 可以查询出来
        Assertions.assertNull(this.autoDelVersionMapper.selectById(demo.getId()));



    }


}
