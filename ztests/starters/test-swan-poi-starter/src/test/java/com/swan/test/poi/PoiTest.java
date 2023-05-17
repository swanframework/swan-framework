package com.swan.test.poi;

import cn.hutool.core.util.RandomUtil;
import com.swan.poi.service.IExcelService;
import com.swan.test.poi.domain.UserDO;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zongf
 * @since 2022-11-10
 **/
@SpringBootTest
public class PoiTest {

    @Autowired
    private IExcelService excelService;


    @Test
    public void getContent() throws Exception{

        List<UserDO> doList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            UserDO userDO = new UserDO();
            userDO.setId(1000 + i);
            userDO.setUsername("zhangs_" + i);
            userDO.setPassword("123456");
            userDO.setCreateTime(new Date());
            userDO.setRemark("<a href=\"http://www.baidu.com\">百度</a>");
            userDO.setMoney(new BigDecimal("123.123456789"));
            userDO.setLg(1.0/3);
            doList.add(userDO);
        }

        Workbook wb = excelService.write("UserDo", doList, UserDO.class);


        FileOutputStream fos = new FileOutputStream("test.xls");
        wb.write(fos);




    }

    @Test
    public void importData() throws Exception{
        FileInputStream fis = new FileInputStream("test.xls");
        List<UserDO> readList = excelService.read(fis, UserDO.class, 0);
        readList.forEach(System.out::println);
    }





}
