package com.swan.test.knife4j.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongf
 * @since 2022-11-09
 **/
@Tag(name = "OrderController", description = "订单接口")
@RestController
@RequestMapping("/order")
public class OrderController {


    @Operation(description = "订单详情")
    @GetMapping("/detail")
    public String detail(String name) {

        return "hello";
    }


}
