package com.swan.test.mybatis.mapper.delete;

 
import com.swan.core.constants.NumberConstant;
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseMapperTest;
import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @date 2021-01-10
 */
public class DeleteListByIdTest extends BaseMapperTest {

    @Test
    public void autoMapper(){
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(3);
        int saveNum = this.autoMapper.insertList(demoList);

        // 验证插入数量一致
        Assertions.assertEquals(demoList.size(), saveNum);

        // 验证自动回填id
        for (AutoEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
        }

        // 删除前两个
        List<Long> idList = Arrays.asList(demoList.get(0).getId(), demoList.get(1).getId());
        int delNum = this.autoMapper.deleteByIds(idList);

        // 验证删除数量
        Assertions.assertEquals(idList.size(), delNum);

        // 验证数据库中只剩下一条记录
        List<AutoEntity> entityList = this.autoMapper.selectList(null);
        Assertions.assertEquals(NumberConstant.ONE, entityList.size());
        Assertions.assertEquals(demoList.get(2).getId(), entityList.get(0).getId());

    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(3);
        int saveNum = this.autoDelMapper.insertList(demoList);

        // 验证插入数量一致 和 自动回填 id, 但不自动回填删除标识
        Assertions.assertEquals(demoList.size(), saveNum);
        for (AutoDelEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
            Assertions.assertNull(demo.getDel());
        }

        // 删除前两个
        List<Long> idList = Arrays.asList(demoList.get(0).getId(), demoList.get(1).getId());
        int delNum = this.autoDelMapper.deleteByIds(idList);

        // 验证删除数量
        Assertions.assertEquals(idList.size(), delNum);

        // 验证数据库中只剩下一条记录，且是第3条记录
        List<AutoDelEntity> entityList = this.autoDelMapper.selectList(null);
        Assertions.assertEquals(NumberConstant.ONE, entityList.size());
        Assertions.assertEquals(demoList.get(2).getId(), entityList.get(0).getId());
        Assertions.assertEquals(DEL_NO, entityList.get(0).getDel());

        // 验证前两条数据还存在，只是删除标志
        AutoDelEntity autoDelEntity = this.autoDelMapper.locateById(entityList.get(0).getId());
        Assertions.assertNotNull(autoDelEntity);
        Assertions.assertEquals(DEL_YES, autoDelEntity.getDel());
    }

    // 有@version 字段时, deleteById 也不会考虑版本号
    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(3);
        int saveNum = this.autoVersionMapper.insertList(demoList);

        // 验证插入数量一致
        Assertions.assertEquals(demoList.size(), saveNum);

        // 验证自动回填id
        for (AutoVersionEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
        }

        // 删除前两个
        List<Long> idList = Arrays.asList(demoList.get(0).getId(), demoList.get(1).getId());
        int delNum = this.autoVersionMapper.deleteByIds(idList);

        // 验证删除数量
        Assertions.assertEquals(idList.size(), delNum);

        // 验证数据库中只剩下一条记录
        List<AutoEntity> entityList = this.autoMapper.selectList(null);
        Assertions.assertEquals(NumberConstant.ONE, entityList.size());
        Assertions.assertEquals(demoList.get(2).getId(), entityList.get(0).getId());

    }

    // 有@version 字段时, deleteById 也不会考虑版本号
    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(3);
        int saveNum = this.autoDelVersionMapper.insertList(demoList);

        // 验证插入数量一致 和 自动回填 id, 但不自动回填删除标识
        Assertions.assertEquals(demoList.size(), saveNum);
        for (AutoDelVersionEntity demo : demoList) {
            Assertions.assertNotNull(demo.getId());
            Assertions.assertNull(demo.getDel());
        }

        // 删除前两个
        List<Long> idList = Arrays.asList(demoList.get(0).getId(), demoList.get(1).getId());
        int delNum = this.autoDelVersionMapper.deleteByIds(idList);

        // 验证删除数量
        Assertions.assertEquals(idList.size(), delNum);

        // 验证数据库中只剩下一条记录，且是第3条记录
        List<AutoDelVersionEntity> entityList = this.autoDelVersionMapper.selectList(null);
        Assertions.assertEquals(NumberConstant.ONE, entityList.size());
        Assertions.assertEquals(demoList.get(2).getId(), entityList.get(0).getId());
        Assertions.assertEquals(DEL_NO, entityList.get(0).getDel());

        // 验证前两条数据还存在，只是修改了删除标志和版本号
        AutoDelVersionEntity autoDelEntity = this.autoDelVersionMapper.locateById(entityList.get(0).getId());
        Assertions.assertNotNull(autoDelEntity);
        Assertions.assertEquals(DEL_YES, autoDelEntity.getDel());
        Assertions.assertEquals(START_VERSION + 1, autoDelEntity.getVersion());

    }


}
