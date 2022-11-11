package com.swan.test.mybatis.mapper.remove;

 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.condition.AutoCondition;
import com.swan.test.mybatis.condition.AutoDelCondition;
import com.swan.test.mybatis.condition.AutoDelVersionCondition;
import com.swan.test.mybatis.condition.AutoVersionCondition;
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
public class RemoveOnCondtionTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5, "zhang_", "lisi_");
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        // 删除成功, 但未生效
        int remove = this.autoMapper.removeOnCondition(new AutoCondition());
        Assertions.assertEquals(demoList.size(), remove);

        List<Long> idList = demoList.stream().map(AutoEntity::getId).collect(Collectors.toList());
        Map<Long, AutoEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        // 验证未删除
        List<AutoEntity> entityList = this.autoMapper.selectListInIds(idList);
        for (AutoEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            map.remove(entity.getId());
        }
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5, "zhang_", "lisi_");
        int num = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        AutoDelCondition condition = new AutoDelCondition("lisi");

        // 无@Delete, 删除成功, 版本号自增1
        int remove = this.autoDelMapper.removeOnCondition(condition);
        Assertions.assertEquals(5, remove);

        // 验证已删除成功
        List<AutoDelEntity> list = this.autoDelMapper.selectListOnCondition(condition);
        Assertions.assertEquals(0, list.size());

        Map<Long, AutoDelEntity> map = new HashMap<>();
        demoList.forEach(demo -> {
            if (demo.getName().contains(condition.getName())) {
                map.put(demo.getId(), demo);
            }
        });

        // 验证locate
        List<AutoDelEntity> entityList = this.autoDelMapper.selectListOnCondition(condition);
        for (AutoDelEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            map.remove(entity.getId());
        }
    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5, "zhang_", "lisi_");
        int num = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        // 删除成功, 但未生效
        int remove = this.autoVersionMapper.removeOnCondition(new AutoVersionCondition());
        Assertions.assertEquals(demoList.size(), remove);

        List<Long> idList = demoList.stream().map(AutoVersionEntity::getId).collect(Collectors.toList());
        Map<Long, AutoVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        // 验证未删除
        List<AutoVersionEntity> entityList = this.autoVersionMapper.selectListInIds(idList);
        for (AutoVersionEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            map.remove(entity.getId());
        }
    }

    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang_", "lisi_");
        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        AutoDelVersionCondition condition = new AutoDelVersionCondition("lisi");

        // 无@Delete, 删除成功, 版本号自增1
        int remove = this.autoDelVersionMapper.removeOnCondition(condition);
        Assertions.assertEquals(5, remove);

        // 验证已删除成功
        List<AutoDelVersionEntity> list = this.autoDelVersionMapper.selectListOnCondition(condition);
        Assertions.assertEquals(0, list.size());

        Map<Long, AutoDelVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> {
            if (demo.getName().contains(condition.getName())) {
                map.put(demo.getId(), demo);
            }
        });

        // 验证locate
        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectListOnCondition(condition);
        for (AutoDelVersionEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(START_VERSION + 1, entity.getVersion().intValue());
            map.remove(entity.getId());
        }
    }


}
