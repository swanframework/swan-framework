package com.swan.test.mybatis;

import com.swan.test.mybatis.mapper.IAutoDelMapper;
import com.swan.test.mybatis.mapper.IAutoDelVersionMapper;
import com.swan.test.mybatis.mapper.IAutoMapper;
import com.swan.test.mybatis.mapper.IAutoVersionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/** 测试基础类
 * @author zongf
 * @since 2020-11-27
 */
@SpringBootTest
@ActiveProfiles("dev")
public class BaseMapperTest {

    @Autowired
    protected IAutoMapper autoMapper;

    @Autowired
    protected IAutoDelMapper autoDelMapper;

    @Autowired
    protected IAutoVersionMapper autoVersionMapper;

    @Autowired
    protected IAutoDelVersionMapper autoDelVersionMapper;

    protected static final Integer START_VERSION = 10;
    protected static final Integer DEL_YES = -1;
    protected static final Integer DEL_NO = 0;

    @BeforeEach
    protected void setUp() {
        System.out.println("\n**************************************************************************************************");
        this.autoMapper.truncate();
        this.autoDelMapper.truncate();
        this.autoVersionMapper.truncate();
        this.autoDelVersionMapper.truncate();
        System.out.println("**************************************************************************************************\n");
    }

}
