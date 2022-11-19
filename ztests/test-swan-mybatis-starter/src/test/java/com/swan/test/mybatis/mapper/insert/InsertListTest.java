package com.swan.test.mybatis.mapper.insert;

import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @date 2021-01-10
 */
public class InsertListTest extends BaseMapperTest {

    @Test
    public void autoMapper(){

        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);

        int num = this.autoMapper.insertList(demoList);

        // 验证插入数量
        Assertions.assertEquals(demoList.size(), num);

        // 验证自动回填 id
        for (AutoEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
        }

        Map<Long, AutoEntity> demoMap = new HashMap<>(demoList.size());
        demoList.forEach(demo -> demoMap.put(demo.getId(), demo));

        List<AutoEntity> entityList = this.autoMapper.selectListByIds(new ArrayList<>(demoMap.keySet()));

        // 验证字段相同
        for (AutoEntity entity : entityList) {
            AutoEntity demo = demoMap.get(entity.getId());
            Assertions.assertEquals(demo.getId(), entity.getId());
            Assertions.assertEquals(demo.getName(), entity.getName());
            Assertions.assertEquals(demo.getAge(), entity.getAge());
            Assertions.assertNotNull(demo.getCreateTime());
            Assertions.assertNotNull(demo.getUpdateTime());
        }
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5);

        int num = this.autoDelMapper.insertList(demoList);

        // 验证插入数量
        Assertions.assertEquals(demoList.size(), num);

        for (AutoDelEntity demo : demoList) {
            // 验证自动回填id
            Assertions.assertNotNull(demo.getId());
            // 验证不自动回填 删除标识
            Assertions.assertNull(demo.getDel());
        }

        Map<Long, AutoDelEntity> demoMap = new HashMap<>(demoList.size());
        demoList.forEach(demo -> demoMap.put(demo.getId(), demo));

        List<AutoDelEntity> entityList = this.autoDelMapper.selectListByIds(new ArrayList<>(demoMap.keySet()));

        // 验证字段一致
        for (AutoDelEntity entity : entityList) {
            AutoDelEntity demo = demoMap.get(entity.getId());
            Assertions.assertEquals(demo.getId(), entity.getId());
            Assertions.assertEquals(demo.getName(), entity.getName());
            Assertions.assertEquals(demo.getAge(), entity.getAge());
            Assertions.assertEquals(DEL_NO, entity.getDel());
        }
    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5);

        int num = this.autoVersionMapper.insertList(demoList);

        // 验证插入数量
        Assertions.assertEquals(demoList.size(), num);

        for (AutoVersionEntity demo : demoList) {
            // 验证自动回填id
            Assertions.assertNotNull(demo.getId());
            // 验证不自动回填版本号
            Assertions.assertNull(demo.getVersion());
        }

        Map<Long, AutoVersionEntity> demoMap = new HashMap<>(demoList.size());
        demoList.forEach(demo -> demoMap.put(demo.getId(), demo));

        List<AutoVersionEntity> entityList = this.autoVersionMapper.selectListByIds(new ArrayList<>(demoMap.keySet()));
        for (AutoVersionEntity entity : entityList) {
            AutoVersionEntity demo = demoMap.get(entity.getId());
            Assertions.assertEquals(demo.getId(), entity.getId());
            Assertions.assertEquals(demo.getName(), entity.getName());
            Assertions.assertEquals(demo.getAge(), entity.getAge());
            Assertions.assertEquals(START_VERSION, entity.getVersion());
        }
    }

    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5);

        int num = this.autoDelVersionMapper.insertList(demoList);

        // 验证插入数量
        Assertions.assertEquals(demoList.size(), num);

        for (AutoDelVersionEntity demo : demoList) {
            // 验证自动回填 id
            Assertions.assertNotNull(demo.getId());
            // 验证不自动回填删除标识
            Assertions.assertNull(demo.getDel());
            // 验证不自动回填版本号
            Assertions.assertNull(demo.getVersion());
        }

        Map<Long, AutoDelVersionEntity> demoMap = new HashMap<>(demoList.size());
        demoList.forEach(demo -> demoMap.put(demo.getId(), demo));

        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectListByIds(new ArrayList<>(demoMap.keySet()));
        // 验证属性一致
        for (AutoDelVersionEntity entity : entityList) {
            AutoDelVersionEntity demo = demoMap.get(entity.getId());
            Assertions.assertEquals(demo.getId(), entity.getId());
            Assertions.assertEquals(demo.getName(), entity.getName());
            Assertions.assertEquals(demo.getAge(), entity.getAge());
            Assertions.assertEquals(START_VERSION, entity.getVersion());
            Assertions.assertEquals(DEL_NO, entity.getDel());
            demoMap.remove(demo.getId());
        }
    }


}
