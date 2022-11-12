package com.swan.test.mybatis.mapper.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
 
import org.junit.jupiter.api.Test;
import com.swan.test.mybatis.condition.AutoCondition;
import com.swan.test.mybatis.mapper.AutoEntityFactory;
import com.swan.test.mybatis.BaseAutoMapperTest;
import com.swan.test.mybatis.po.AutoEntity;

import java.util.List;
import org.junit.jupiter.api.Assertions;
/** 分页: 利用开源的 pagehelper 实现
 *  1. 页码从0 开始
 *  2. 采用 lampda 表达式，设置分页，设置是否查询总数
 *  3. 返回的Page 说明:
 *      1. count: 是否查询总数量
 * @author zongf
 * @date 2021-01-10
 */
public class PageHelperTest extends BaseAutoMapperTest {

    @Test
    public void autoMapper(){
        List<AutoEntity> demoList = AutoEntityFactory.createAutoEntity(10, "zhang", "lisi");
        int num = this.autoMapper.insertList(demoList);
        Assertions.assertEquals(demoList.size(), num);

         AutoCondition condition = new AutoCondition("lisi");

        Page<AutoEntity> pager = PageHelper
                .startPage(-1, 2)
                .count(true)
                .reasonable(false)
                .pageSizeZero(false)
                .doSelectPage(() -> this.autoMapper.selectListOnCondition(condition))
                ;

        System.out.println(pager);
        Assertions.assertEquals(pager.getPages(), demoList.size() /2 / pager.getPageSize());
        Assertions.assertEquals(pager.getTotal(), demoList.size()/2);
    }
}