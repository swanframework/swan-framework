package com.swan.test.mybatis.mapper.update;

 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.BaseMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;
import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @date 2021-01-10
 */
public class UpdateTest extends BaseMapperTest {

    // 无@Version 时, 只根据id更新
    @Test
    public void autoMapper(){
        AutoEntity demo = new AutoEntity("zhangsan", 20);
        boolean success = this.autoMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        demo.setName("lisi");
        demo.setAge(null);
        boolean updateSuccess = this.autoMapper.update(demo);
        Assertions.assertTrue(updateSuccess);

        AutoEntity entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertEquals("lisi", entity.getName());
        Assertions.assertNull(entity.getAge());
    }

    // 无@Version 时, 只根据id更新
    @Test
    public void autoDelMapper(){

        AutoDelEntity demo = new AutoDelEntity("zhangsan", 20);
        boolean success = this.autoDelMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        demo.setName("lisi");
        demo.setAge(null);
        boolean updateSuccess = this.autoDelMapper.update(demo);
        Assertions.assertTrue(updateSuccess);

        AutoDelEntity entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertEquals("lisi", entity.getName());
        Assertions.assertNull(entity.getAge());
    }

    // 有@Version 时, 会根据id 和版本号进行更新, 更新后版本号自增1
    @Test
    public void autoVersionMapper(){

        AutoVersionEntity demo = new AutoVersionEntity("zhangsan", 20);
        boolean success = this.autoVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        demo.setName("lisi");
        demo.setAge(30);
        boolean updateSuccess = this.autoVersionMapper.update(demo);
        Assertions.assertFalse(updateSuccess);

        AutoVersionEntity entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertEquals("zhangsan", entity.getName());
        Assertions.assertEquals(20, entity.getAge().intValue());
        Assertions.assertNotNull(entity.getVersion());

        entity.setName("wangwu");
        entity.setAge(null);
        boolean update = this.autoVersionMapper.update(entity);
        Assertions.assertTrue(update);

        // 更新成功后, 版本号增加1
        AutoVersionEntity entity1 = this.autoVersionMapper.selectById(entity.getId());
        Assertions.assertEquals("wangwu", entity1.getName());
        Assertions.assertNull(entity1.getAge());
        Assertions.assertEquals(Integer.valueOf(entity.getVersion() + 1), entity1.getVersion());

    }

    @Test
    public void autoDelVersionMapper(){

        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan", 20);
        boolean success = this.autoDelVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        demo.setName("lisi");
        demo.setAge(30);
        boolean updateSuccess = this.autoDelVersionMapper.update(demo);
        Assertions.assertFalse(updateSuccess);

        AutoDelVersionEntity entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertEquals("zhangsan", entity.getName());
        Assertions.assertEquals(20, entity.getAge().intValue());
        Assertions.assertNotNull(entity.getVersion());

        entity.setName("wangwu");
        entity.setAge(null);
        boolean update = this.autoDelVersionMapper.update(entity);
        Assertions.assertTrue(update);

        // 更新成功后, 版本号增加1
        AutoDelVersionEntity entity1 = this.autoDelVersionMapper.selectById(entity.getId());
        Assertions.assertEquals("wangwu", entity1.getName());
        Assertions.assertNull(entity1.getAge());
        Assertions.assertEquals(Integer.valueOf(entity.getVersion() + 1), entity1.getVersion());

    }


}
