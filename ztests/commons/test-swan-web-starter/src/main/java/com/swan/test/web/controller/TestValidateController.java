package com.swan.test.web.controller;

import com.swan.test.web.request.UserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zongf
 * @date 2021-10-26
 */
@RestController
@RequestMapping("/test")
public class TestValidateController {

    @GetMapping("/list")
    public String list(@Valid UserRequest request) {
        return "hello";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id) {
        int a = 100 /id;
        return "hello:" + id;
    }
}
