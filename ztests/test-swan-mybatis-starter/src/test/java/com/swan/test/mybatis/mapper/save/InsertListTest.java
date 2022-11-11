package com.swan.test.mybatis.mapper.save;

 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseAutoMapperTest;
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
public class InsertListTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){

        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);

        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        for (AutoEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
        }

        Map<Long, AutoEntity> demoMap = new HashMap<>(demoList.size());
        demoList.forEach(demo -> demoMap.put(demo.getId(), demo));

        List<AutoEntity> entityList = this.autoMapper.selectListInIds(new ArrayList<>(demoMap.keySet()));
        for (AutoEntity entity : entityList) {
            AutoEntity demo = demoMap.get(entity.getId());
            Assertions.assertEquals(demo.getName(), entity.getName());
            Assertions.assertEquals(demo.getAge(), entity.getAge());
            demoMap.remove(demo.getId());
        }
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5);

        int num = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        for (AutoDelEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
            Assertions.assertNull(demo.getDel());
        }

        Map<Long, AutoDelEntity> demoMap = new HashMap<>(demoList.size());
        demoList.forEach(demo -> demoMap.put(demo.getId(), demo));

        List<AutoDelEntity> entityList = this.autoDelMapper.selectListInIds(new ArrayList<>(demoMap.keySet()));
        for (AutoDelEntity entity : entityList) {
            AutoDelEntity demo = demoMap.get(entity.getId());
            Assertions.assertEquals(demo.getName(), entity.getName());
            Assertions.assertEquals(demo.getAge(), entity.getAge());
            Assertions.assertEquals(DEL_NO, entity.getDel());
            demoMap.remove(demo.getId());
        }
    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5);

        int num = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        for (AutoVersionEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
            Assertions.assertNull(demo.getVersion());
        }

        Map<Long, AutoVersionEntity> demoMap = new HashMap<>(demoList.size());
        demoList.forEach(demo -> demoMap.put(demo.getId(), demo));

        List<AutoVersionEntity> entityList = this.autoVersionMapper.selectListInIds(new ArrayList<>(demoMap.keySet()));
        for (AutoVersionEntity entity : entityList) {
            AutoVersionEntity demo = demoMap.get(entity.getId());
            Assertions.assertEquals(demo.getName(), entity.getName());
            Assertions.assertEquals(demo.getAge(), entity.getAge());
            Assertions.assertEquals(START_VERSION, entity.getVersion());
            demoMap.remove(demo.getId());
        }
    }

    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5);

        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        for (AutoDelVersionEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
            Assertions.assertNull(demo.getVersion());
            Assertions.assertNull(demo.getDel());
        }

        Map<Long, AutoDelVersionEntity> demoMap = new HashMap<>(demoList.size());
        demoList.forEach(demo -> demoMap.put(demo.getId(), demo));

        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectListInIds(new ArrayList<>(demoMap.keySet()));
        for (AutoDelVersionEntity entity : entityList) {
            AutoDelVersionEntity demo = demoMap.get(entity.getId());
            Assertions.assertEquals(demo.getName(), entity.getName());
            Assertions.assertEquals(demo.getAge(), entity.getAge());
            Assertions.assertEquals(START_VERSION, entity.getVersion());
            Assertions.assertEquals(DEL_NO, entity.getDel());
            demoMap.remove(demo.getId());
        }
    }


}
