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
public class DeleteListByIdTest
        extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(3);
        int saveNum = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), saveNum);

        List<Long> idList = demoList.stream().map(AutoEntity::getId).collect(Collectors.toList());
        int delNum = this.autoMapper.deleteInIds(idList);
        Assertions.assertEquals(demoList.size(), delNum);

        for (Long id : idList) {
            AutoEntity entity = this.autoMapper.selectById(id);
            Assertions.assertNull(entity);
        }
    }

    @Test
    public void autoDelMapper(){

        List<AutoDelEntity> demoList = AutoEntityFactory.createAutoDelEntity(3);
        int saveNum = this.autoDelMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), saveNum);

        List<Long> idList = demoList.stream().map(AutoDelEntity::getId).collect(Collectors.toList());
        int delNum = this.autoDelMapper.deleteInIds(idList);
        Assertions.assertEquals(demoList.size(), delNum);

        for (Long id : idList) {
            AutoDelEntity entity = this.autoDelMapper.selectById(id);
            Assertions.assertNull(entity);
        }
    }

    // 有@version 字段时, deleteById 也不会考虑版本号
    @Test
    public void autoVersionMapper(){

        List<AutoVersionEntity> demoList = AutoEntityFactory.createAutoVersionEntity(3);
        int saveNum = this.autoVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), saveNum);

        // 有@version 时, 也不会参考version
        List<Long> idList = demoList.stream().map(AutoVersionEntity::getId).collect(Collectors.toList());
        int delNum = this.autoVersionMapper.deleteInIds(idList);
        Assertions.assertEquals(demoList.size(), delNum);

        for (Long id : idList) {
            AutoVersionEntity entity = this.autoVersionMapper.selectById(id);
            Assertions.assertNull(entity);
        }

    }

    // 有@version 字段时, deleteById 也不会考虑版本号
    @Test
    public void autoDelVersionMapper(){

        List<AutoDelVersionEntity> demoList = AutoEntityFactory.createAutoDelVersionEntity(3);
        int saveNum = this.autoDelVersionMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), saveNum);

        // 有@version 时, 也不会参考version
        List<Long> idList = demoList.stream().map(AutoDelVersionEntity::getId).collect(Collectors.toList());
        int delNum = this.autoDelVersionMapper.deleteInIds(idList);
        Assertions.assertEquals(demoList.size(), delNum);

        for (Long id : idList) {
            AutoDelVersionEntity entity = this.autoDelVersionMapper.selectById(id);
            Assertions.assertNull(entity);
        }

    }


}
