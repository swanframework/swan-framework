package com.swan.test.mybatis.service;

import com.swan.test.mybatis.mapper.IAutoMapper;
import com.swan.test.mybatis.po.AutoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zongf
 * @created: 2021-01-18
 * @since 1.0
 */
@Service
public class AutoService implements IAutoService{

    @Autowired
    private IAutoMapper autoMapper;

    @Transactional
    public void testTx(boolean ex){

        AutoEntity zhangsan = new AutoEntity("zhangsan");
        this.autoMapper.insert(zhangsan);

        if (ex) {
            int a = 1/0;
            System.out.println(a);
        }

        AutoEntity lisi = new AutoEntity("lisi");
        this.autoMapper.insert(lisi);

    }


}
