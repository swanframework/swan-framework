package com.swan.test.freemarker;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author zongf
 * @since 2022-11-10
 **/
@SpringBootTest
public class FreemarkerTest {

    @Autowired
    private DefaultKaptcha charKaptcha;

    @Autowired
    private DefaultKaptcha mathKaptcha;

    @Test
    public void getContent() throws Exception{

        String capText = mathKaptcha.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = mathKaptcha.createImage(capStr);


        ImageIO.write(image, "png", new File("hello.png"));

    }



}
