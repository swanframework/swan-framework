package com.swan.test.mybatis.condition;

 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoEntity;

import java.util.List;

/**
 * @author zongf
 * @date 2021-01-10
 */
public class ConditionInterceptorTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){

        AutoCondition condition = new AutoCondition("lisi");
        List<AutoEntity> entityList = this.autoMapper.selectListOnCondition(condition);
        Assertions.assertEquals(5, entityList.size());

    }




}
