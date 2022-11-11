package com.swan.test.mybatis.mapper.query;

 
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
import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @date 2021-01-10
 */
public class SelectListOnConditionTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5, "zhang", "lisi");
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        AutoCondition condition = new AutoCondition("lisi");
        List<AutoEntity> entityList = this.autoMapper.selectListOnCondition(condition);
        Assertions.assertEquals(5, entityList.size());

        Map<Long, AutoEntity> map = new HashMap<>();
        demoList.forEach(demo -> {
            if (demo.getName().startsWith(condition.getName())) {
                map.put(demo.getId(), demo);
            }
        });

        for (AutoEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getId(), entity.getId());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            map.remove(entity.getId());
        }

        // 测试删除
        int remove = this.autoMapper.deleteOnCondition(condition);
        Assertions.assertEquals(entityList.size(), remove);

        entityList = this.autoMapper.selectListOnCondition(condition);
        Assertions.assertEquals(0, entityList.size());
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(5, "zhangs", "lisi");
        int num = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        AutoDelCondition condition = new AutoDelCondition("lisi");
        List<AutoDelEntity> entityList = this.autoDelMapper.selectListOnCondition(condition);
        Assertions.assertEquals(5, entityList.size());

        Map<Long, AutoDelEntity> map = new HashMap<>();
        demoList.forEach(demo -> {
            if (demo.getName().startsWith(condition.getName())) {
                map.put(demo.getId(), demo);
            }
        });

        for (AutoDelEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getId(), entity.getId());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(DEL_NO, entity.getDel());
            map.remove(entity.getId());
        }

        // 测试删除
        int remove = this.autoDelMapper.removeOnCondition(condition);
        Assertions.assertEquals(entityList.size(), remove);

        entityList = this.autoDelMapper.selectListOnCondition(condition);
        Assertions.assertEquals(0, entityList.size());

    }

    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(5, "zhang", "lisi");
        int num = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        AutoVersionCondition condition = new AutoVersionCondition("lisi");
        List<AutoVersionEntity> entityList = this.autoVersionMapper.selectListOnCondition(condition);
        Assertions.assertEquals(5, entityList.size());

        Map<Long, AutoVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> {
            if (demo.getName().startsWith(condition.getName())) {
                map.put(demo.getId(), demo);
            }
        });

        for (AutoVersionEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getId(), entity.getId());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(map.get(entity.getId()).getAge(), entity.getAge());
            Assertions.assertEquals(START_VERSION, entity.getVersion());
            map.remove(entity.getId());
        }

        // 测试删除
        int remove = this.autoVersionMapper.deleteOnCondition(condition);
        Assertions.assertEquals(entityList.size(), remove);

        entityList = this.autoVersionMapper.selectListOnCondition(condition);
        Assertions.assertEquals(0, entityList.size());

    }

    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang", "lisi");
        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        AutoDelVersionCondition condition = new AutoDelVersionCondition("lisi");
        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectListOnCondition(condition);
        Assertions.assertEquals(5, entityList.size());

        Map<Long, AutoDelVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> {
            if (demo.getName().startsWith(condition.getName())) {
                map.put(demo.getId(), demo);
            }
        });

        for (AutoDelVersionEntity entity : entityList) {
            Assertions.assertEquals(map.get(entity.getId()).getId(), entity.getId());
            Assertions.assertEquals(map.get(entity.getId()).getName(), entity.getName());
            Assertions.assertEquals(DEL_NO, entity.getDel());
            map.remove(entity.getId());
        }

        // 测试删除
        int remove = this.autoDelVersionMapper.removeOnCondition(condition);
        Assertions.assertEquals(entityList.size(), remove);

        entityList = this.autoDelVersionMapper.selectListOnCondition(condition);
        Assertions.assertEquals(0, entityList.size());
    }


}
