package com.swan.test.mybatis.mapper.select;


import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.OpType;
import com.swan.mybatis.condition.SelectOption;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.po.AutoEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author zongf
 * @date 2021-01-10
 */
public class SelectListTest extends BaseAutoMapperTest {

    @Test
    public void selectList() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(3, "zhang");
        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(5, "li");
        this.autoMapper.insertList(zhangList);
        this.autoMapper.insertList(liList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.name, OpType.leftLike, "zhang");

        SelectOption option = SelectOption.newInstance()
                .distinct()
                .fields(AutoEntity.Fields.age)
                .asc(AutoEntity.Fields.age)
                .limit(0, 1);
        List<AutoEntity> list = this.autoMapper.selectList(condition, option);

    }


}
