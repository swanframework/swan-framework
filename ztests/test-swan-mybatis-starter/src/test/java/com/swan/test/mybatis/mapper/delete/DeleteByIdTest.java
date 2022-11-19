package com.swan.test.mybatis.mapper.delete;

 
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
public class DeleteByIdTest extends BaseMapperTest {

    @Test
    public void autoMapper(){
        AutoEntity demo = new AutoEntity("zhangsan", 20);
        boolean success = this.autoMapper.insert(demo);

        // 验证插入成功
        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        // 验证可查询到
        AutoEntity entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(demo.getId(), entity.getId());

        // 验证删除
        boolean delSuccess = this.autoMapper.deleteById(demo.getId());
        Assertions.assertTrue(delSuccess);

        // 验证查询不到
        entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

        // 验证删除不存在的记录
        success = this.autoMapper.deleteById(demo.getId());
        Assertions.assertFalse(success);
    }

    @Test
    public void autoDelMapper(){

        AutoDelEntity demo = new AutoDelEntity("zhangsan", 20);
        boolean success = this.autoDelMapper.insert(demo);

        // 验证插入成功
        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        // 验证可查询到
        AutoDelEntity entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(demo.getId(), entity.getId());

        // 伪删除
        boolean delSuccess = this.autoDelMapper.deleteById(demo.getId());
        Assertions.assertTrue(delSuccess);

        // 验证通过普通方式已查询不出来
        entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

        // 通过物理查询，可查询到
        entity = this.autoDelMapper.locateById(demo.getId());
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(DEL_YES, entity.getDel());

        // 验证删除不存在的记录
        success = this.autoDelMapper.deleteById(demo.getId());
        Assertions.assertFalse(success);
    }

    // 有@version 字段时, deleteById 也不会考虑版本号
    @Test
    public void autoVersionMapper(){

        AutoVersionEntity demo = new AutoVersionEntity("zhangsan", 20);
        boolean success = this.autoVersionMapper.insert(demo);

        // 验证插入成功
        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());
        Assertions.assertNull(demo.getVersion());

        AutoVersionEntity entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(demo.getId(), entity.getId());

        // 验证删除成功
        success = this.autoVersionMapper.deleteById(demo.getId());
        Assertions.assertTrue(success);

        // 验证查询不到记录
        entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

        // 验证删除不存在的记录
        success = this.autoVersionMapper.deleteById(demo.getId());
        Assertions.assertFalse(success);
    }

    // 有@version 字段时, deleteById 也不会考虑版本号
    @Test
    public void autoDelVersionMapper(){

        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan", 20);
        boolean success = this.autoDelVersionMapper.insert(demo);

        // 验证插入成功
        Assertions.assertTrue(success);
        Assertions.assertNotNull(demo.getId());

        // 验证可查询到
        AutoDelVersionEntity entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(demo.getId(), entity.getId());

        // 伪删除
        boolean delSuccess = this.autoDelVersionMapper.deleteById(demo.getId());
        Assertions.assertTrue(delSuccess);

        // 验证通过普通方式已查询不出来
        entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

        // 通过物理查询，可查询到
        entity = this.autoDelVersionMapper.locateById(demo.getId());
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(DEL_YES, entity.getDel());

        // 验证删除不存在的记录
        success = this.autoDelVersionMapper.deleteById(demo.getId());
        Assertions.assertFalse(success);
    }


}
