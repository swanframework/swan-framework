package com.swan.test.mybatis.mapper.query;

 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @date 2021-01-10
 */
public class SelectListInIdsTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoEntity::getId).collect(Collectors.toList());
        List<AutoEntity> entityList = this.autoMapper.selectListInIds(idList);

        Map<Long, AutoEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        for (AutoEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getId(), entity.getId());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            map.remove(entity.getId());
        }

        // 测试删除
        int remove = this.autoMapper.deleteByIds(idList);
        Assertions.assertEquals(idList.size(), remove);

        entityList = this.autoMapper.selectListInIds(idList);
        Assertions.assertEquals(0, entityList.size());
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5);
        int num = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoDelEntity::getId).collect(Collectors.toList());
        List<AutoDelEntity> entityList = this.autoDelMapper.selectListInIds(idList);

        Map<Long, AutoDelEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        for (AutoDelEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getId(), entity.getId());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            map.remove(entity.getId());
        }

        // 测试删除
        int remove = this.autoDelMapper.deleteByIds(idList);
        Assertions.assertEquals(idList.size(), remove);

        entityList = this.autoDelMapper.selectListInIds(idList);
        Assertions.assertEquals(0, entityList.size());

    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5);
        int num = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoVersionEntity::getId).collect(Collectors.toList());
        List<AutoVersionEntity> entityList = this.autoVersionMapper.selectListInIds(idList);

        Map<Long, AutoVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        for (AutoVersionEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getId(), entity.getId());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            map.remove(entity.getId());
        }

        // 测试删除
        int remove = this.autoVersionMapper.deleteByIds(idList);
        Assertions.assertEquals(idList.size(), remove);

        entityList = this.autoVersionMapper.selectListInIds(idList);
        Assertions.assertEquals(0, entityList.size());

    }

    @Test
    public void autoDelVersionMapper(){
        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5);
        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoDelVersionEntity::getId).collect(Collectors.toList());
        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectListInIds(idList);

        Map<Long, AutoDelVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        for (AutoDelVersionEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getId(), entity.getId());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            map.remove(entity.getId());
        }

        // 测试删除
        int remove = this.autoDelVersionMapper.deleteByIds(idList);
        Assertions.assertEquals(idList.size(), remove);

        entityList = this.autoDelVersionMapper.selectListInIds(idList);
        Assertions.assertEquals(0, entityList.size());

    }


}
