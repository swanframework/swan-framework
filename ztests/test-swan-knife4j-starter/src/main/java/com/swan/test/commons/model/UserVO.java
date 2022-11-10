package com.swan.test.commons.model;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zongf
 * @since 2022-11-09
 **/
@Schema(name = "用户信息")
@Setter @Getter
public class UserVO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "年龄", title = "合法年龄", required = true, minimum = "0", maximum = "200")
    private String age;

    @Schema(description = "用户名")
    private String password;



}
