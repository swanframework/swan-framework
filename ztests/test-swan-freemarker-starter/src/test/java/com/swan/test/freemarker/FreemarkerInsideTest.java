package com.swan.test.freemarker;

import com.swan.freemarker.core.IFreemarkerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.Name;

/**
 * @author zongf
 * @since 2022-11-10
 **/
@SpringBootTest
public class FreemarkerInsideTest {

    @Autowired
    @Qualifier("freemarkerTemplateInside")
    private IFreemarkerTemplate freemarkerTemplate;

    @Test
    public void getContent() {


        String content = this.freemarkerTemplate.getContent("template1.ftl", "name", "zongf");
        System.out.println(content);
    }



}
