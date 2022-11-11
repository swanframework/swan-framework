package com.swan.test.mybatis.mapper.delete;

 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @date 2021-01-10
 */
public class DeleteListTest extends BaseAutoMapperTest {

    // 无@Version 时, batchDelete 只比对id
    @Test
    public void autoMapper(){

        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(5);

        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

        for (AutoEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
        }

        int delNum = this.autoMapper.deleteList(demoList);
        Assertions.assertEquals(demoList.size(), delNum);

        for (AutoEntity entity : demoList) {
            AutoEntity entity1 = this.autoMapper.selectById(entity.getId());
            Assertions.assertNull(entity1);
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

        int delNum = this.autoDelMapper.deleteList(demoList);
        Assertions.assertEquals(demoList.size(), delNum);

        for (AutoDelEntity entity : demoList) {
            AutoDelEntity entity1 = this.autoDelMapper.selectById(entity.getId());
            Assertions.assertNull(entity1);
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

        // 删除失败, 因为demoList 中版本号为null
        int delNum = this.autoVersionMapper.deleteList(demoList);
        Assertions.assertEquals(delNum, 0);

        List<Long> idList = demoList.stream().map(AutoVersionEntity::getId).collect(Collectors.toList());
        List<AutoVersionEntity> entityList = this.autoVersionMapper.selectListInIds(idList);
        // 验证未删除
        Assertions.assertEquals(idList.size(), entityList.size());

        // 验证版本号不为空
        for (AutoVersionEntity entity : entityList) {
            Assertions.assertNotNull(entity.getVersion());
        }

        // 更新一次, 保证entityList版本号不一致
        boolean update = this.autoVersionMapper.update(entityList.get(1));
        AutoVersionEntity updateEntity = this.autoVersionMapper.selectById(entityList.get(1).getId());
        entityList.set(1, updateEntity);

        Assertions.assertTrue(update);
        Assertions.assertEquals(Integer.valueOf(entityList.get(0).getVersion() + 1), entityList.get(1).getVersion());

        // 删除成功
        int delNum1 = this.autoVersionMapper.deleteList(entityList);
        Assertions.assertEquals(entityList.size(), delNum1);

        // 验证删除成功
        for (AutoVersionEntity entity : demoList) {
            AutoVersionEntity entity1 = this.autoVersionMapper.selectById(entity.getId());
            Assertions.assertNull(entity1);
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
        }

        // 删除失败, 因为demoList 中版本号为null
        int delNum = this.autoDelVersionMapper.deleteList(demoList);
        Assertions.assertEquals(delNum, 0);

        List<Long> idList = demoList.stream().map(AutoDelVersionEntity::getId).collect(Collectors.toList());
        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectListInIds(idList);
        // 验证未删除
        Assertions.assertEquals(idList.size(), entityList.size());

        // 验证版本号不为空
        for (AutoDelVersionEntity entity : entityList) {
            Assertions.assertNotNull(entity.getVersion());
        }

        // 更新一次, 保证entityList版本号不一致
        boolean update = this.autoDelVersionMapper.update(entityList.get(1));
        AutoDelVersionEntity updateEntity = this.autoDelVersionMapper.selectById(entityList.get(1).getId());
        entityList.set(1, updateEntity);

        Assertions.assertTrue(update);
        Assertions.assertEquals(Integer.valueOf(entityList.get(0).getVersion() + 1), entityList.get(1).getVersion());

        // 删除成功
        int delNum1 = this.autoDelVersionMapper.deleteList(entityList);
        Assertions.assertEquals(entityList.size(), delNum1);

        // 验证删除成功
        for (AutoDelVersionEntity entity : demoList) {
            AutoDelVersionEntity entity1 = this.autoDelVersionMapper.selectById(entity.getId());
            Assertions.assertNull(entity1);
        }

    }


}
