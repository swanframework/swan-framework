package com.swan.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zongf
 * @date 2021-10-26
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/list")
    public String list() {
        return "hello";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id) {
        // 未知异常
        int a = 100 /id;

        return "hello:" + id;
    }
}
