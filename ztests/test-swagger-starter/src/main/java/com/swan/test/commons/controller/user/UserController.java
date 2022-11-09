package com.swan.test.commons.controller.user;

import com.swan.test.commons.model.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @author zongf
 * @since 2022-11-09
 **/
@Tag(name = "UserController", description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {


    @Operation(description = "创建用户")
    @PostMapping("/create")
    public String create(@RequestBody UserVO userVO) {

        return "hello";
    }


}
