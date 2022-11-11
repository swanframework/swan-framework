package com.swan.test.mybatis.tx;

 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.swan.mybatis.anno.config.EnableAutoMapper;
import com.swan.test.mybatis.mapper.IAutoMapper;
import com.swan.test.mybatis.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
/** mybatis 事务测试
 * @author zongf
 * @date 2021-01-19
 */
@SpringBootTest
@EnableAutoMapper
public class TxTest {

    @Autowired
    protected IAutoMapper autoMapper;

    @Autowired
    private AutoService autoService;

    @BeforeEach
    protected void setUp() {
        System.out.println("\n**************************************************************************************************");
        this.autoMapper.truncate();
    }


    @Test
    public void testFail(){
//        Assertions.assertThrows(ArithmeticException.class, ()->{
//            this.autoService.testTx(true);
//
//        });
        Integer count = this.autoMapper.count();
        Assertions.assertEquals(0, count.intValue());
    }

    @Test
    public void testSuccess(){
        this.autoService.testTx(false);

        Integer count = this.autoMapper.count();
        Assertions.assertEquals(2, count.intValue());
    }

}
