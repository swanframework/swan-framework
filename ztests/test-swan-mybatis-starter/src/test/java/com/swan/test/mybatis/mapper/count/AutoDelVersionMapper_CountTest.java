package com.swan.test.mybatis.mapper.count;


import com.swan.core.constants.NumberConstant;
import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.OpType;
import com.swan.test.mybatis.BaseMapperTest;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zongf
 * @date 2021-01-10
 */
public class AutoDelVersionMapper_CountTest extends BaseMapperTest {

    @Test
    public void none_param() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        // 验证数量
        int count = this.autoDelVersionMapper.count(null);
        Assertions.assertEquals(zhangList.size(), count);

        List<AutoDelVersionEntity> liList = AutoEntityFactory.createAutoDelVersionEntity(10, "li");
        this.autoDelVersionMapper.insertList(liList);

        // 验证数量
        count = this.autoDelVersionMapper.count(null);
        Assertions.assertEquals(liList.size() + zhangList.size(), count);
    }

    @Test
    public void equal() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        List<AutoDelVersionEntity> liList = AutoEntityFactory.createAutoDelVersionEntity(10, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);
        this.autoDelVersionMapper.insertList(liList);

        Condition condition = Condition.newInstance();

        // 通过id查询,预期只有一个
        condition.clear()
                .and(AutoDelVersionEntity.Fields.id, OpType.equals, zhangList.get(0).getId());
        int count = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.ONE , count);

        // 通过 age 查询，预期有两个
        condition.clear()
                .and(AutoDelVersionEntity.Fields.age, OpType.equals, zhangList.get(0).getAge());
        int zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.TWO, zhangCount);
    }


    @Test
    public void notEquals() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoDelVersionEntity.Fields.id, OpType.notEquals, zhangList.get(0).getId());
        int count = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(zhangList.size() -1 , count);
    }

    @Test
    public void lessThan() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(10, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoDelVersionEntity.Fields.id, OpType.lessThan, zhangList.get(5).getId());
        int count = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.FIVE , count);
    }

    @Test
    public void lessOrEquals() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(10, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有4个数据
        Condition condition = Condition.newInstance()
                .and(AutoDelVersionEntity.Fields.id, OpType.lessOrEquals, zhangList.get(5).getId());
        int count = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.SIX , count);
    }

    @Test
    public void greaterThan() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(10, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有4个数据
        Condition condition = Condition.newInstance()
                .and(AutoDelVersionEntity.Fields.id, OpType.greaterThan, zhangList.get(5).getId());
        int count = this.autoDelVersionMapper.count(condition);

        // 预期结果: 7，8，9,10
        Assertions.assertEquals(NumberConstant.FOUR , count);
    }

    @Test
    public void greaterOrEquals() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(10, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有5个数据
        Condition condition = Condition.newInstance()
                .and(AutoDelVersionEntity.Fields.id, OpType.greaterOrEquals, zhangList.get(5).getId());
        int count = this.autoDelVersionMapper.count(condition);

        // 预期结果: 6，7，8，9,10
        Assertions.assertEquals(NumberConstant.FIVE, count);
    }

    @Test
    public void between() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(10, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoDelVersionEntity.Fields.id, OpType.between, 1,6);
        int count = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.SIX , count);
    }

    @Test
    public void notBetween() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(10, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoDelVersionEntity.Fields.id, OpType.notBetween, 1,6);
        int count = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.FOUR , count);
    }

    @Test
    public void in() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        List<AutoDelVersionEntity> liList = AutoEntityFactory.createAutoDelVersionEntity(10, "li");

        // 保存两份数据
        this.autoDelVersionMapper.insertList(zhangList);
        this.autoDelVersionMapper.insertList(liList);

        Condition condition = Condition.newInstance();
        List<Long> zhangIds = zhangList.stream().map(AutoDelVersionEntity::getId).collect(Collectors.toList());

        // 通过id列表
        condition.clear()
                .and(AutoDelVersionEntity.Fields.id, OpType.in, zhangIds.toArray());
        int zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(zhangIds.size(), zhangCount);
    }

    @Test
    public void notIn() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        List<AutoDelVersionEntity> liList = AutoEntityFactory.createAutoDelVersionEntity(10, "li");

        // 保存两份数据
        this.autoDelVersionMapper.insertList(zhangList);
        this.autoDelVersionMapper.insertList(liList);

        Condition condition = Condition.newInstance();
        List<Long> zhangIds = zhangList.stream().map(AutoDelVersionEntity::getId).collect(Collectors.toList());

        // 通过id列表
        condition.clear()
                .and(AutoDelVersionEntity.Fields.id, OpType.notIn, zhangIds.toArray());
        int zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(liList.size(), zhangCount);
    }


    @Test
    public void leftLike() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        List<AutoDelVersionEntity> liList = AutoEntityFactory.createAutoDelVersionEntity(10, "li");

        // 保存两份数据
        this.autoDelVersionMapper.insertList(zhangList);
        this.autoDelVersionMapper.insertList(liList);

        // 查询全部
        Condition condition = Condition.newInstance();

        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoDelVersionEntity.Fields.name, OpType.leftLike, "zhang");
        int zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(zhangList.size() , zhangCount);
    }

    @Test
    public void leftNotLike() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        List<AutoDelVersionEntity> liList = AutoEntityFactory.createAutoDelVersionEntity(10, "li");

        // 保存两份数据
        this.autoDelVersionMapper.insertList(zhangList);
        this.autoDelVersionMapper.insertList(liList);

        // 查询全部
        Condition condition = Condition.newInstance();

        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoDelVersionEntity.Fields.name, OpType.leftNotLike, "zhang");
        int zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(liList.size() , zhangCount);
    }

    @Test
    public void rightLike() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        // 查询全部
        Condition condition = Condition.newInstance();

        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoDelVersionEntity.Fields.name, OpType.rightLike, "1");
        int zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.ONE , zhangCount);
    }

    @Test
    public void rightNotLike() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        // 查询全部
        Condition condition = Condition.newInstance();

        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoDelVersionEntity.Fields.name, OpType.rightNotLike, "1");
        int zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(zhangList.size() -1 , zhangCount);
    }

    @Test
    public void isNull() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        Condition condition = Condition.newInstance();

        // 通过id列表
        condition.clear()
                .and(AutoDelVersionEntity.Fields.id, OpType.isNull);
        int zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.ZERO, zhangCount);

        // 构造一个为null 的数据
        AutoDelVersionEntity AutoDelVersionEntity = new AutoDelVersionEntity();
        AutoDelVersionEntity.setName("ageIsNull");
        this.autoDelVersionMapper.insert(AutoDelVersionEntity);

        condition.clear()
                .and(com.swan.test.mybatis.po.AutoDelVersionEntity.Fields.age, OpType.isNull);
        zhangCount = this.autoDelVersionMapper.count(condition);
        Assertions.assertEquals(NumberConstant.ONE, zhangCount);
    }

    @Test
    public void isNotNull() {
        List<AutoDelVersionEntity> zhangList = AutoEntityFactory.createAutoDelVersionEntity(5, "zhang");
        this.autoDelVersionMapper.insertList(zhangList);

        // 构造一个为null 的数据
        AutoDelVersionEntity AutoDelVersionEntity = new AutoDelVersionEntity();
        AutoDelVersionEntity.setName("ageIsNull");
        this.autoDelVersionMapper.insert(AutoDelVersionEntity);

        // 总数加1
        int count = this.autoDelVersionMapper.count(null);
        Assertions.assertEquals(zhangList.size() + 1, count);

        //只查询 agent 为null的
        Condition condition = Condition.newInstance()
                .and(com.swan.test.mybatis.po.AutoDelVersionEntity.Fields.age, OpType.isNotNull);
        int zhangCount = this.autoDelVersionMapper.count(condition);

        Assertions.assertEquals(zhangList.size() , zhangCount);

    }







}
