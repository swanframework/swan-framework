package com.swan.test.mybatis.mapper.select;


import com.swan.core.constants.NumberConstant;
import com.swan.mybatis.condition.Condition;
import com.swan.mybatis.condition.OpType;
import com.swan.mybatis.condition.SelectOption;
import com.swan.test.mybatis.BaseMapperTest;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.po.AutoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.swan.test.mybatis.po.AutoEntity.Fields.*;

/**
 * @author zongf
 * @since 2021-01-10
 */
public class SelectListTest extends BaseMapperTest {

    @Test
    public void noneParam() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(3, "zhang");
        this.autoMapper.insertList(zhangList);

        // 查询全部，默认id 升序, 默认查询全部字段
        List<AutoEntity> poList = this.autoMapper.selectList(null);
        for (AutoEntity po : poList) {
            Assertions.assertNotNull(po.getId());
            Assertions.assertNotNull(po.getName());
            Assertions.assertNotNull(po.getAge());
            Assertions.assertNotNull(po.getCreateTime());
            Assertions.assertNotNull(po.getUpdateTime());
        }

        // 验证为id 升序
        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(poList.get(i).getId(), zhangList.get(i).getId());
        }
    }

    @Test
    public void option_orderBy_desc() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(3, "zhang");
        this.autoMapper.insertList(zhangList);

        SelectOption option = SelectOption.newInstance().desc(id);

        // 查询全部，默认id 升序, 默认查询全部字段
        List<AutoEntity> poList = this.autoMapper.selectList(null, option);
        for (AutoEntity po : poList) {
            Assertions.assertNotNull(po.getId());
            Assertions.assertNotNull(po.getName());
            Assertions.assertNotNull(po.getAge());
            Assertions.assertNotNull(po.getCreateTime());
            Assertions.assertNotNull(po.getUpdateTime());
        }

        // 验证为id 降序
        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(poList.get(i).getId(), zhangList.get(poList.size()-1 - i).getId());
        }
    }

    @Test
    public void option_fields() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(3, "zhang");
        this.autoMapper.insertList(zhangList);

        SelectOption option = SelectOption.newInstance()
                .fields(id, name, age)
                .desc(id);

        // 查询全部，只查询 id,name,age 字段
        List<AutoEntity> poList = this.autoMapper.selectList(null, option);
        for (AutoEntity po : poList) {
            Assertions.assertNotNull(po.getId());
            Assertions.assertNotNull(po.getName());
            Assertions.assertNotNull(po.getAge());
            Assertions.assertNull(po.getCreateTime());
            Assertions.assertNull(po.getUpdateTime());
        }

        // 验证为id 降序
        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(poList.get(i).getId(), zhangList.get(poList.size()-1 - i).getId());
        }
    }

    @Test
    public void option_distinct_fields() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        List<AutoEntity> liList = AutoEntityFactory.createAutoEntity(5, "li");
        this.autoMapper.insertList(zhangList);
        this.autoMapper.insertList(liList);

        SelectOption option = SelectOption.newInstance()
                .distinct()
                .fields(age)
                .desc(age);

        // 查询全部，只查询 id,name,age 字段
        List<AutoEntity> poList = this.autoMapper.selectList(null, option);

        // 验证数量只有一半
        Assertions.assertEquals(zhangList.size(), poList.size());

        // 验证字段只有 age有值
        for (AutoEntity po : poList) {
            Assertions.assertNull(po.getId());
            Assertions.assertNull(po.getName());
            Assertions.assertNotNull(po.getAge());
            Assertions.assertNull(po.getCreateTime());
            Assertions.assertNull(po.getUpdateTime());
        }

        // 验证为id 降序
        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(poList.get(i).getAge(), zhangList.get(poList.size()-1 - i).getAge());
        }
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
        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(NumberConstant.ONE , poList.size());

        // 验证字段, 时间字段可能有毫秒偏差，所以只判非空
        Assertions.assertEquals(zhangList.get(0).getId() , poList.get(0).getId());
        Assertions.assertEquals(zhangList.get(0).getName() , poList.get(0).getName());
        Assertions.assertEquals(zhangList.get(0).getAge() , poList.get(0).getAge());
        Assertions.assertNotNull(poList.get(0).getCreateTime());
        Assertions.assertNotNull(poList.get(0).getUpdateTime());

    }


    @Test
    public void notEquals() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.notEquals, zhangList.get(0).getId());
        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(zhangList.size() -1 , poList.size());

        Assertions.assertFalse(poList.stream().map(AutoEntity::getId).collect(Collectors.toList()).contains(zhangList.get(0).getId()));
    }

    @Test
    public void lessThan() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.lessThan, zhangList.get(1).getId());
        List<AutoEntity> poList = this.autoMapper.selectList(condition);

        // 验证为第一个元素
        Assertions.assertEquals(NumberConstant.ONE , poList.size());
        Assertions.assertEquals(zhangList.get(0).getId(), poList.get(0).getId());
    }

    @Test
    public void lessOrEquals() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有4个数据
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.lessOrEquals, zhangList.get(0).getId());
        List<AutoEntity> poList = this.autoMapper.selectList(condition);

        // 验证为第一个元素
        Assertions.assertEquals(NumberConstant.ONE , poList.size());
        Assertions.assertEquals(zhangList.get(0).getId(), poList.get(0).getId());;
    }

    @Test
    public void greaterThan() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有4个数据
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.greaterThan, zhangList.get(8).getId());
        List<AutoEntity> poList = this.autoMapper.selectList(condition);

        // 验证为最后一个元素
        Assertions.assertEquals(NumberConstant.ONE , poList.size());
        Assertions.assertEquals(zhangList.get(9).getId(), poList.get(0).getId());
    }

    @Test
    public void greaterOrEquals() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        // zhangList.get(5).getId() = 6, 后面还有5个数据
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.greaterOrEquals, zhangList.get(9).getId());
        List<AutoEntity> poList = this.autoMapper.selectList(condition);

        // 验证为最后一个元素
        Assertions.assertEquals(NumberConstant.ONE , poList.size());
        Assertions.assertEquals(zhangList.get(9).getId(), poList.get(0).getId());
    }

    @Test
    public void between() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.between, 10, 10);
        List<AutoEntity> poList = this.autoMapper.selectList(condition);

        // 验证为最后一个元素
        Assertions.assertEquals(NumberConstant.ONE , poList.size());
        Assertions.assertEquals(zhangList.get(9).getId(), poList.get(0).getId());
    }

    @Test
    public void notBetween() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(10, "zhang");
        this.autoMapper.insertList(zhangList);

        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.id, OpType.notBetween, 2,10);
        List<AutoEntity> poList = this.autoMapper.selectList(condition);

        // 验证为第1个元素
        Assertions.assertEquals(NumberConstant.ONE , poList.size());
        Assertions.assertEquals(zhangList.get(0).getId(), poList.get(0).getId());
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
                .and(AutoEntity.Fields.id, OpType.in, zhangIds.toArray());

        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(zhangList.size(), poList.size());

        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(zhangList.get(i).getId(), poList.get(i).getId());
        }
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
                .and(AutoEntity.Fields.id, OpType.notIn, zhangIds.toArray());
        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(liList.size(), poList.size());

        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(liList.get(i).getId(), poList.get(i).getId());
        }
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

        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(zhangList.size(), poList.size());

        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(zhangList.get(i).getId(), poList.get(i).getId());
        }
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
        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(liList.size(), poList.size());

        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(liList.get(i).getId(), poList.get(i).getId());
        }
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

        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(NumberConstant.ONE, poList.size());

        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(zhangList.get(i).getId(), poList.get(i).getId());
        }
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

        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(zhangList.size() -1, poList.size());

        // 不包含第一个元素
        Assertions.assertFalse(poList.stream().map(AutoEntity::getId).collect(Collectors.toList()).contains(zhangList.get(0).getId()));
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

        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(NumberConstant.ONE, poList.size());
        Assertions.assertEquals(autoEntity.getId(), poList.get(0).getId());
    }

    @Test
    public void isNotNull() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        // 构造一个为null 的数据
        AutoEntity autoEntity = new AutoEntity();
        autoEntity.setName("ageIsNull");
        this.autoMapper.insert(autoEntity);

        //只查询 agent 为null的
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.age, OpType.isNotNull);

        List<AutoEntity> poList = this.autoMapper.selectList(condition);
        Assertions.assertEquals(zhangList.size(), poList.size());

        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(zhangList.get(i).getId(), poList.get(i).getId());
        }
    }

    @Test
    public void option_condition() {
        List<AutoEntity> zhangList = AutoEntityFactory.createAutoEntity(5, "zhang");
        this.autoMapper.insertList(zhangList);

        //只查询 agent 为null的
        Condition condition = Condition.newInstance()
                .and(AutoEntity.Fields.age, OpType.isNotNull);

        SelectOption option = SelectOption.newInstance()
                .fields(id, name, age)
                .desc(id);

        List<AutoEntity> poList = this.autoMapper.selectList(condition, option);

        Assertions.assertEquals(zhangList.size(), poList.size());
        for (AutoEntity po : poList) {
            Assertions.assertNotNull(po.getId());
            Assertions.assertNotNull(po.getName());
            Assertions.assertNotNull(po.getAge());
            Assertions.assertNull(po.getCreateTime());
            Assertions.assertNull(po.getUpdateTime());
        }

        for (int i = 0; i < poList.size(); i++) {
            Assertions.assertEquals(poList.get(i).getId(), zhangList.get(poList.size()-1 - i).getId());
        }
    }










}
