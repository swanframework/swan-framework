package com.swan.test.mybatis.mapper.delete;


import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author zongf
 * @date 2021-01-10
 */
public class DeleteTest extends BaseAutoMapperTest {

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
        boolean delSuccess = this.autoMapper.delete(demo);
        Assertions.assertTrue(delSuccess);

        // 验证查询不到
        entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

        // 验证删除不存在的记录
        success = this.autoMapper.delete(demo);
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
        boolean delSuccess = this.autoDelMapper.delete(demo);
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
        success = this.autoDelMapper.delete(demo);
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

        // 验证删除失败，demo 默认无版本号
        success = this.autoVersionMapper.delete(demo);
        Assertions.assertFalse(success);

        // 依然可以查询到记录
        entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertNotNull(entity);

        // 版本号一致
        success = this.autoVersionMapper.delete(entity);
        Assertions.assertTrue(success);

        // 验证查询不到记录
        entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

        // 验证删除不存在的记录
        success = this.autoVersionMapper.delete(demo);
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

        // 伪删除失败，版本号为空
        boolean delSuccess = this.autoDelVersionMapper.delete(demo);
        Assertions.assertFalse(delSuccess);

        entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertNotNull(entity);

        // 带版本号删除
        delSuccess = this.autoDelVersionMapper.delete(entity);
        Assertions.assertTrue(delSuccess);

        // 验证通过普通方式已查询不出来
        entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertNull(entity);

        // 通过物理查询，可查询到
        entity = this.autoDelVersionMapper.locateById(demo.getId());
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(DEL_YES, entity.getDel());
        Assertions.assertEquals(START_VERSION + 1, entity.getVersion());

        // 验证删除不存在的记录
        success = this.autoDelVersionMapper.delete(demo);
        Assertions.assertFalse(success);
    }


}
