package com.swan.test.mybatis.mapper.delete;

 
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
public class DeleteByIdTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        AutoEntity demo = new AutoEntity("zhangsan", 20);
        boolean success = this.autoMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        boolean delSuccess = this.autoMapper.deleteById(demo.getId());
        Assertions.assertTrue(delSuccess);

        AutoEntity entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

    }

    @Test
    public void autoDelMapper(){

        AutoDelEntity demo = new AutoDelEntity("zhangsan", 20);
        boolean success = this.autoDelMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        boolean delSuccess = this.autoDelMapper.deleteById(demo.getId());
        Assertions.assertTrue(delSuccess);

        AutoDelEntity entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

    }

    // 有@version 字段时, deleteById 也不会考虑版本号
    @Test
    public void autoVersionMapper(){

        AutoVersionEntity demo = new AutoVersionEntity("zhangsan", 20);
        boolean success = this.autoVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getVersion());

        // 验证删除成功
        AutoVersionEntity entity1 = this.autoVersionMapper.selectById(demo.getId());
        success = this.autoVersionMapper.deleteById(entity1.getId());
        Assertions.assertTrue(success);
        AutoVersionEntity newEntity = this.autoVersionMapper.selectById(entity1.getId());
        Assertions.assertNull(newEntity);
    }

    // 有@version 字段时, deleteById 也不会考虑版本号
    @Test
    public void autoDelVersionMapper(){

        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan", 20);
        boolean success = this.autoDelVersionMapper.insert(demo);

        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getVersion());

        // 验证删除成功
        AutoDelVersionEntity entity1 = this.autoDelVersionMapper.selectById(demo.getId());
        success = this.autoDelVersionMapper.deleteById(entity1.getId());
        Assertions.assertTrue(success);
        AutoDelVersionEntity newEntity = this.autoDelVersionMapper.selectById(entity1.getId());
        Assertions.assertNull(newEntity);

    }


}
