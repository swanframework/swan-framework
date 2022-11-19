package com.swan.test.mybatis.mapper.insert;


import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

/**
 * @author zongf
 * @date 2021-01-10
 */
public class InsertNotNullTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        AutoEntity demo = new AutoEntity("zhangsan");
        demo.setAge(20);
        demo.setCreateTime(new Date());
        demo.setUpdateTime(new Date());
        boolean success = this.autoMapper.insertNotNull(demo);

        // 验证插入成功
        Assertions.assertTrue(success);
        // 验证自动回填id
        Assertions.assertNotNull(demo.getId());

        AutoEntity entity = this.autoMapper.selectById(demo.getId());

        // 验证字段一致
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertNotNull(demo.getCreateTime());
        Assertions.assertNotNull(demo.getUpdateTime());
    }

    @Test
    public void autoDelMapper(){
        AutoDelEntity demo = new AutoDelEntity("zhangsan");
        boolean success = this.autoDelMapper.insertNotNull(demo);

        // 验证插入成功
        Assertions.assertTrue(success);
        // 验证自动回填 id
        Assertions.assertNotNull(demo.getId());
        // 验证不自动回填删除标识
        Assertions.assertNull(demo.getDel());

        AutoDelEntity entity = this.autoDelMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(DEL_NO, entity.getDel());
    }

    @Test
    public void autoVersionMapper(){
        AutoVersionEntity demo = new AutoVersionEntity("zhangsan");
        boolean success = this.autoVersionMapper.insertNotNull(demo);

        // 验证插入成功
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
        AutoDelVersionEntity demo = new AutoDelVersionEntity("zhangsan");
        boolean success = this.autoDelVersionMapper.insertNotNull(demo);

        // 验证插入成功
        Assertions.assertTrue(success);
        // 验证自动回填id
        Assertions.assertNotNull(demo.getId());
        // 验证自动回填 id
        Assertions.assertNotNull(demo.getId());
        // 验证不自动回填删除标识
        Assertions.assertNull(demo.getDel());
        // 验证不自动回填版本号
        Assertions.assertNull(demo.getVersion());

        // 验证字段一致
        AutoDelVersionEntity entity = this.autoDelVersionMapper.selectById(demo.getId());
        Assertions.assertEquals(demo.getId(), entity.getId());
        Assertions.assertEquals(demo.getName(), entity.getName());
        Assertions.assertEquals(demo.getAge(), entity.getAge());
        Assertions.assertEquals(START_VERSION, entity.getVersion());
        Assertions.assertEquals(DEL_NO, entity.getDel());
    }


}
