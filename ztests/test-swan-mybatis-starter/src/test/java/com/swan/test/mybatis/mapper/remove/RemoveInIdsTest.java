package com.swan.test.mybatis.mapper.remove;

 
import org.junit.jupiter.api.Assertions;
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

/**
 * @author zongf
 * @date 2021-01-10
 */
public class RemoveInIdsTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoEntity::getId).collect(Collectors.toList());

        // 无@Delete, 删除虽然成功, 但是并未真正成功
        int remove = this.autoMapper.removeInIds(idList);
        Assertions.assertEquals(demoList.size(), remove);

        Map<Long, AutoEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        // 验证未删除
        List<AutoEntity> entityList = this.autoMapper.selectListInIds(idList);
        for (AutoEntity entity : entityList) {
            Assertions.assertEquals(entity.getAge(), map.get(entity.getId()).getAge());
            Assertions.assertEquals(entity.getName(), map.get(entity.getId()).getName());
            map.remove(entity.getId());
        }
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5);
        int num = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoDelEntity::getId).collect(Collectors.toList());

        // 无@Delete, 删除成功, 版本号自增1
        int remove = this.autoDelMapper.removeInIds(idList);
        Assertions.assertEquals(demoList.size(), remove);

        Map<Long, AutoDelEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        // 验证成功删除
        List<AutoDelEntity> list = this.autoDelMapper.selectListInIds(idList);
        Assertions.assertEquals(0, list.size());

        // 验证locate
        List<AutoDelEntity> entityList = this.autoDelMapper.selectListInIds(idList);
        for (AutoDelEntity entity : entityList) {
            Assertions.assertEquals(entity.getAge(), map.get(entity.getId()).getAge());
            Assertions.assertEquals(entity.getName(), map.get(entity.getId()).getName());
            map.remove(entity.getId());
        }
    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5);
        int num = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoVersionEntity::getId).collect(Collectors.toList());

        // 无@Delete, 删除虽然成功, 但是并未真正成功
        int remove = this.autoVersionMapper.removeInIds(idList);
        Assertions.assertEquals(demoList.size(), remove);

        Map<Long, AutoVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        // 验证未删除, 但是版本号自增1
        List<AutoVersionEntity> entityList = this.autoVersionMapper.selectListInIds(idList);
        for (AutoVersionEntity entity : entityList) {
            Assertions.assertEquals(entity.getAge(), map.get(entity.getId()).getAge());
            Assertions.assertEquals(entity.getName(), map.get(entity.getId()).getName());
            Assertions.assertEquals(START_VERSION +1, entity.getVersion().intValue());
            map.remove(entity.getId());
        }
    }

    @Test
    public void autoDelVersionMapper(){
        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5);
        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoDelVersionEntity::getId).collect(Collectors.toList());

        // 无@Delete, 删除成功, 版本号自增1
        int remove = this.autoDelVersionMapper.removeInIds(idList);
        Assertions.assertEquals(demoList.size(), remove);

        Map<Long, AutoDelVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        // 验证成功删除
        List<AutoDelVersionEntity> list = this.autoDelVersionMapper.selectListInIds(idList);
        Assertions.assertEquals(0, list.size());

        // 验证locate
        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectListInIds(idList);
        for (AutoDelVersionEntity entity : entityList) {
            Assertions.assertEquals(entity.getAge(), map.get(entity.getId()).getAge());
            Assertions.assertEquals(entity.getName(), map.get(entity.getId()).getName());
            Assertions.assertEquals(START_VERSION + 1, entity.getVersion().intValue());
            map.remove(entity.getId());
        }

    }


}
