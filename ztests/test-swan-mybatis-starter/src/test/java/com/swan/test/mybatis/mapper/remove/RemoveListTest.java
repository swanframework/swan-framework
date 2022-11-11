package com.swan.test.mybatis.mapper.remove;

 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zongf
 * @date 2021-01-10
 */
public class RemoveListTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        List<Long> idList = demoList.stream().map(AutoEntity::getId).collect(Collectors.toList());

        // 无@Delete, 删除虽然成功, 但是并未真正成功
        int remove = this.autoMapper.removeList(demoList);
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

        // 无@Delete, 版本号不一致, 删除失败
        int remove = this.autoVersionMapper.removeList(demoList);
        Assertions.assertEquals(0, remove);

        // 重新重数据库加载
        List<Long> idList = demoList.stream().map(AutoVersionEntity::getId).collect(Collectors.toList());
        List<AutoVersionEntity> entiyList = this.autoVersionMapper.selectListInIds(idList);

        // 第一个多更新一次
        boolean update = this.autoVersionMapper.update(entiyList.get(0));
        Assertions.assertTrue(update);

        // 替换第0号,元素
        AutoVersionEntity entity0 = this.autoVersionMapper.selectById(entiyList.get(0).getId());
        entiyList.set(0, entity0);

        // 删除
        int remove1 = this.autoVersionMapper.removeList(entiyList);
        Assertions.assertEquals(entiyList.size(), remove1);

        Map<Long, AutoVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        // 验证未删除, 但是版本号自增1
        List<AutoVersionEntity> entityList = this.autoVersionMapper.selectListInIds(idList);

        for (int i = 0; i < entityList.size(); i++) {
            AutoVersionEntity entity = entityList.get(i);

            Assertions.assertEquals(entity.getAge(), map.get(entity.getId()).getAge());
            Assertions.assertEquals(entity.getName(), map.get(entity.getId()).getName());
            if (i == 0) {
                Assertions.assertEquals(START_VERSION +2, entity.getVersion().intValue());
            }else {
                Assertions.assertEquals(START_VERSION +1, entity.getVersion().intValue());
            }
            map.remove(entity.getId());
        }
    }

    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(5);
        int num = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        // 版本号不一致, 删除失败
        int remove = this.autoDelVersionMapper.removeList(demoList);
        Assertions.assertEquals(0, remove);

        // 重新重数据库加载
        List<Long> idList = demoList.stream().map(AutoDelVersionEntity::getId).collect(Collectors.toList());
        List<AutoDelVersionEntity> entiyList = this.autoDelVersionMapper.selectListInIds(idList);

        // 第一个多更新一次
        boolean update = this.autoDelVersionMapper.update(entiyList.get(0));
        Assertions.assertTrue(update);

        // 替换第0号,元素
        AutoDelVersionEntity entity0 = this.autoDelVersionMapper.selectById(entiyList.get(0).getId());
        entiyList.set(0, entity0);

        // 删除
        int remove1 = this.autoDelVersionMapper.removeList(entiyList);
        Assertions.assertEquals(entiyList.size(), remove1);

        // 验证删除成功
        List<AutoDelVersionEntity> list = this.autoDelVersionMapper.selectListInIds(idList);
        Assertions.assertEquals(0, list.size());

        Map<Long, AutoDelVersionEntity> map = new HashMap<>();
        demoList.forEach(demo -> map.put(demo.getId(), demo));

        // 验证未删除, 但是版本号自增1
        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectListInIds(idList);

        for (int i = 0; i < entityList.size(); i++) {
            AutoDelVersionEntity entity = entityList.get(i);

            Assertions.assertEquals(entity.getAge(), map.get(entity.getId()).getAge());
            Assertions.assertEquals(entity.getName(), map.get(entity.getId()).getName());
            if (i == 0) {
                Assertions.assertEquals(START_VERSION +2, entity.getVersion().intValue());
            }else {
                Assertions.assertEquals(START_VERSION +1, entity.getVersion().intValue());
            }
            map.remove(entity.getId());
        }

    }


}
