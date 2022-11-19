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
 * @since 2021-01-10
 */
public class UpdateNotNullByIdTest extends BaseMapperTest {

    // 无@Version 时, 只根据id更新
    @Test
    public void autoMapper(){
        AutoEntity demo = new AutoEntity("zhangsan", 20);
        boolean success = this.autoMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        demo.setName("lisi");
        demo.setAge(null);
        boolean updateSuccess = this.autoMapper.updateNotNullById(demo);
        Assertions.assertTrue(updateSuccess);

        AutoEntity entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertEquals("lisi", entity.getName());
        Assertions.assertEquals(20, entity.getAge().intValue());
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
        boolean updateSuccess = this.autoDelMapper.updateNotNullById(demo);
        Assertions.assertTrue(updateSuccess);

        AutoDelEntity entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertEquals("lisi", entity.getName());
        Assertions.assertEquals(20, entity.getAge().intValue());
    }

    // 有@Version 时, 也只根据id 进行更新, 更新后版本号自增1, 版本号不能手工修改
    @Test
    public void autoVersionMapper(){

        AutoVersionEntity demo = new AutoVersionEntity("zhangsan", 20);
        boolean success = this.autoVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        demo.setName("lisi");
        demo.setAge(null);
        boolean updateSuccess = this.autoVersionMapper.updateNotNullById(demo);
        Assertions.assertTrue(updateSuccess);

        AutoVersionEntity entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertEquals("lisi", entity.getName());
        Assertions.assertEquals(20, entity.getAge().intValue());
        Assertions.assertNotNull(entity.getVersion());

        entity.setName(null);
        entity.setAge(40);
        entity.setVersion(1000);
        boolean update = this.autoVersionMapper.updateNotNullById(entity);
        Assertions.assertTrue(update);

        // 更新成功后, 版本号增加1
        AutoVersionEntity entity1 = this.autoVersionMapper.selectById(entity.getId());
        Assertions.assertEquals("lisi", entity1.getName());
        Assertions.assertEquals(40, entity1.getAge().intValue());

        // 验证手工修改版本号,无效
        Assertions.assertNotEquals(Integer.valueOf(entity.getVersion() + 1), entity1.getVersion());

    }

    @Test
    public void autoDelVersionMapper(){

        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan", 20);
        boolean success = this.autoDelVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        demo.setName("lisi");
        demo.setAge(null);
        boolean updateSuccess = this.autoDelVersionMapper.updateNotNullById(demo);
        Assertions.assertTrue(updateSuccess);

        AutoDelVersionEntity entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertEquals("lisi", entity.getName());
        Assertions.assertEquals(20, entity.getAge().intValue());
        Assertions.assertNotNull(entity.getVersion());

        entity.setName(null);
        entity.setAge(40);
        entity.setVersion(1000);
        boolean update = this.autoDelVersionMapper.updateNotNullById(entity);
        Assertions.assertTrue(update);

        // 更新成功后, 版本号增加1
        AutoDelVersionEntity entity1 = this.autoDelVersionMapper.selectById(entity.getId());
        Assertions.assertEquals("lisi", entity1.getName());
        Assertions.assertEquals(40, entity1.getAge().intValue());

        // 验证手工修改版本号,无效
        Assertions.assertNotEquals(Integer.valueOf(entity.getVersion() + 1), entity1.getVersion());

    }


}
