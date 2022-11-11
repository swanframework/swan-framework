package com.swan.test.mybatis.condition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoEntity;

import java.util.*;

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

        System.out.println("-----------------------------------------");
        List<AutoEntity> autoEntities = this.autoMapper.queryListByIds(Arrays.asList(1, 2, 3));
        System.out.println(autoEntities);


    }



}
