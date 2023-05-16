package com.swan.test.poi;

import com.swan.poi.service.IExcelService;
import com.swan.test.poi.domain.UserDO;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
            doList.add(userDO);
        }

        Workbook wb = excelService.write("UserDo", doList, UserDO.class);


        FileOutputStream fis = new FileOutputStream(new File("test.xls"));
        wb.write(fis);

    }



}
