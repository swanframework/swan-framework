package com.swan.test.mybatis.mapper.count;

 
import com.swan.core.constants.NumberConstant;
import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.OpType;
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseMapperTest;
import com.swan.test.mybatis.po.AutoEntity;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
/**
 * @author zongf
 * @since 2021-01-10
 */
public class AutoDelMapper_CountTest extends BaseMapperTest {

    @Test
    public void none_param() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        // 验证数量
        int count = this.autoMapper.count(null);
        Assertions.assertEquals(zhangList.size(), count);

        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(10, "li");
        this.autoMapper.insertList(liList);

        // 验证数量
        count = this.autoMapper.count(null);
        Assertions.assertEquals(liList.size() + zhangList.size(), count);
    }

    @Test
    public void equal() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);
        this.autoMapper.insertList(liList);

        Condition condition = Condition.newInstance();

        // 通过id查询,预期只有一个
        condition.clear()
                .and(AutoEntity.Fields.id, OpType.equals, zhangList.get(0).getId());
        int count = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.ONE , count);

        // 通过 age 查询，预期有两个
        condition.clear()
                .and(AutoEntity.Fields.age, OpType.equals, zhangList.get(0).getAge());
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.TWO, zhangCount);
    }


    @Test
    public void notEquals() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.notEquals, zhangList.get(0).getId());
        int count = this.autoMapper.count(condition);
        Assertions.assertEquals(zhangList.size() -1 , count);
    }

    @Test
    public void lessThan() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.lessThan, zhangList.get(5).getId());
        int count = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.FIVE , count);
    }

    @Test
    public void lessOrEquals() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有4个数据
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.lessOrEquals, zhangList.get(5).getId());
        int count = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.SIX , count);
    }

    @Test
    public void greaterThan() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有4个数据
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.greaterThan, zhangList.get(5).getId());
        int count = this.autoMapper.count(condition);

        // 预期结果: 7，8，9,10
        Assertions.assertEquals(NumberConstant.FOUR , count);
    }

    @Test
    public void greaterOrEquals() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有5个数据
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.greaterOrEquals, zhangList.get(5).getId());
        int count = this.autoMapper.count(condition);

        // 预期结果: 6，7，8，9,10
        Assertions.assertEquals(NumberConstant.FIVE, count);
    }

    @Test
    public void between() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.between, 1,6);
        int count = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.SIX , count);
    }

    @Test
    public void notBetween() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.notBetween, 1,6);
        int count = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.FOUR , count);
    }

    @Test
    public void in() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(10, "li");

        // 保存两份数据
        this.autoMapper.insertList(zhangList);
        this.autoMapper.insertList(liList);

        Condition condition = Condition.newInstance();
        List<Long> zhangIds = zhangList.stream().map(AutoEntity::getId).collect(Collectors.toList());

        // 通过id列表
        condition.clear()
                .and(AutoEntity.Fields.id, OpType.in, zhangIds);
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(zhangIds.size(), zhangCount);
    }

    @Test
    public void notIn() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(10, "li");

        // 保存两份数据
        this.autoMapper.insertList(zhangList);
        this.autoMapper.insertList(liList);

        Condition condition = Condition.newInstance();
        List<Long> zhangIds = zhangList.stream().map(AutoEntity::getId).collect(Collectors.toList());

        // 通过id列表
        condition.clear()
                .and(AutoEntity.Fields.id, OpType.notIn, zhangIds);
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(liList.size(), zhangCount);
    }


    @Test
    public void leftLike() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(10, "li");

        // 保存两份数据
        this.autoMapper.insertList(zhangList);
        this.autoMapper.insertList(liList);

        // 查询全部
        Condition condition = Condition.newInstance();

        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoEntity.Fields.name, OpType.leftLike, "zhang");
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(zhangList.size() , zhangCount);
    }

    @Test
    public void leftNotLike() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(10, "li");

        // 保存两份数据
        this.autoMapper.insertList(zhangList);
        this.autoMapper.insertList(liList);

        // 查询全部
        Condition condition = Condition.newInstance();

        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoEntity.Fields.name, OpType.leftNotLike, "zhang");
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(liList.size() , zhangCount);
    }

    @Test
    public void rightLike() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        // 查询全部
        Condition condition = Condition.newInstance();

        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoEntity.Fields.name, OpType.rightLike, "1");
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.ONE , zhangCount);
    }

    @Test
    public void rightNotLike() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        // 查询全部
        Condition condition = Condition.newInstance();

        // 只查询 zhang_xxx
        condition.clear()
                .and(AutoEntity.Fields.name, OpType.rightNotLike, "1");
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(zhangList.size() -1 , zhangCount);
    }

    @Test
    public void isNull() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance();

        // 通过id列表
        condition.clear()
                .and(AutoEntity.Fields.id, OpType.isNull);
        int zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.ZERO, zhangCount);

        // 构造一个为null 的数据
        AutoEntity autoEntity = new AutoEntity();
        autoEntity.setName("ageIsNull");
        this.autoMapper.insert(autoEntity);

        condition.clear()
                .and(AutoEntity.Fields.age, OpType.isNull);
        zhangCount = this.autoMapper.count(condition);
        Assertions.assertEquals(NumberConstant.ONE, zhangCount);
    }

    @Test
    public void isNotNull() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        // 构造一个为null 的数据
        AutoEntity autoEntity = new AutoEntity();
        autoEntity.setName("ageIsNull");
        this.autoMapper.insert(autoEntity);

        // 总数加1
        int count = this.autoMapper.count(null);
        Assertions.assertEquals(zhangList.size() + 1, count);

        //只查询 agent 为null的
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.age, OpType.isNotNull);
        int zhangCount = this.autoMapper.count(condition);

        Assertions.assertEquals(zhangList.size() , zhangCount);

    }







}
