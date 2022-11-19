package com.swan.test.freemarker;

import com.swan.freemarker.core.IFreemarkerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zongf
 * @since 2022-11-10
 **/
@SpringBootTest
public class FreemarkerTest {

    @Autowired
    private IFreemarkerTemplate freemarkerTemplate;

    @Test
    public void getContent() {
        Map<String, Object> root = new HashMap<>();
        root.put("date", new Date());
        root.put("time", new Date());
        root.put("float", 1.0f);
        root.put("number", 1.0);

        String content = this.freemarkerTemplate.getContent("template2.ftl", root);
        System.out.println(content);
    }



}
