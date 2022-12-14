package com.swan.test.kaptcha.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zongf
 * @since 2022-12-15
 **/
@RequestMapping
@RestController
public class HelloController {

    @Autowired
    private DefaultKaptcha charKaptcha;

    @Autowired
    private DefaultKaptcha mathKaptcha;

    @GetMapping("/img")
    public void getKaptcha(HttpServletResponse response) throws IOException {
        String text = charKaptcha.createText();
        BufferedImage image = charKaptcha.createImage(text);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }



    @GetMapping("/math")
    public void getContent(HttpServletResponse response) throws Exception{

        String capText = mathKaptcha.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = mathKaptcha.createImage(capStr);


        ImageIO.write(image, "png", response.getOutputStream());
    }
}
