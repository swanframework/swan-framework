package com.swan.test.mybatis.mapper.insert;


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

        // 验证插入成功
        Assertions.assertTrue(success);
        // 验证自动回填id
        Assertions.assertNotNull(demo.getId());

        // 验证字段一致
        AutoEntity entity = this.autoMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertNotNull(demo.getCreateTime());
        Assertions.assertNotNull(demo.getUpdateTime());
    }

    @Test
    public void autoDelMapper(){
        AutoDelEntity demo = new AutoDelEntity("zhangsan", 20);
        boolean success = this.autoDelMapper.insert(demo);

        // 验证插入成功
        Assertions.assertTrue(success);
        // 验证自动回填 id
        Assertions.assertNotNull(demo.getId());
        // 验证不自动回填删除标识
        Assertions.assertNull(demo.getDel());

        // 验证字段一致
        AutoDelEntity entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(DEL_NO, entity.getDel());
    }

    @Test
    public void autoVersionMapper(){
        AutoVersionEntity demo = new AutoVersionEntity("zhangsan", 20);
        boolean success = this.autoVersionMapper.insert(demo);

        // 验证插入成功、版本号、生成id
        Assertions.assertTrue(success);
        // 验证自动回填 id
        Assertions.assertNotNull(demo.getId());
        // 验证不自动回填版本号
        Assertions.assertNull(demo.getVersion());

        // 验证字段一致
        AutoVersionEntity entity = this.autoVersionMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(START_VERSION, entity.getVersion());
    }

    @Test
    public void autoDelVersionMapper(){
        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan", 20);
        boolean success = this.autoDelVersionMapper.insert(demo);

        // 验证插入成功、自动回填id、版本号、删除标识
        Assertions.assertTrue(success);
        // 验证自动回填 id
        Assertions.assertNotNull(demo.getId());
        // 验证不自动回填删除标识
        Assertions.assertNull(demo.getDel());
        // 验证不自动回填版本号
        Assertions.assertNull(demo.getVersion());

        AutoDelVersionEntity entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(START_VERSION, entity.getVersion());
        Assertions.assertEquals(DEL_NO, entity.getDel());
    }


}
