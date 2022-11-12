package com.swan.test.mybatis.mapper.query;

 
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
public class SelectByIdTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        AutoEntity demo = new AutoEntity("zhangsan", 20);
        boolean success = this.autoMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        // 验证查询成功
        AutoEntity entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());

        // 删除
        boolean delete = this.autoMapper.deleteById(demo.getId());
        Assertions.assertTrue(delete);

        // 验证查询失败
        Assertions.assertNull(this.autoMapper.selectById(demo.getId()));
    }

    @Test
    public void autoDelMapper(){
        AutoDelEntity demo = new AutoDelEntity("zhangsan", 20);
        boolean success = this.autoDelMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getDel());

        // 验证查询成功
        AutoDelEntity entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());

        // 删除
        boolean delete = this.autoDelMapper.deleteById(demo.getId());
        Assertions.assertTrue(delete);

        // 验证查询失败
        Assertions.assertNull(this.autoDelMapper.selectById(demo.getId()));

    }

    @Test
    public void autoVersionMapper(){
        AutoVersionEntity demo = new AutoVersionEntity("zhangsan", 20);
        boolean success = this.autoVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        // 验证查询成功
        AutoVersionEntity entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());

        // 删除
        boolean delete = this.autoVersionMapper.deleteById(demo.getId());
        Assertions.assertTrue(delete);

        // 验证查询失败
        Assertions.assertNull(this.autoVersionMapper.selectById(demo.getId()));
    }

    @Test
    public void autoDelVersionMapper(){
        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan", 20);
        boolean success = this.autoDelVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getDel());
        Assertions.assertNull(demo.getVersion());

        // 验证查询成功
        AutoDelVersionEntity entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());

        // 删除
        boolean delete = this.autoDelVersionMapper.deleteById(demo.getId());
        Assertions.assertTrue(delete);

        // 验证查询失败
        Assertions.assertNull(this.autoDelVersionMapper.selectById(demo.getId()));

    }


}
