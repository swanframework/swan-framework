package com.swan.test.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zongf
 * @date 2021-10-26
 */
@Data
public class UserRequest {

    @NotNull
    private Integer id;

    @NotBlank
    private String username;

}
