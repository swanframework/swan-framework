package com.swan.test.rabbitmq.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zongf
 * @date 2020-11-19
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class UserPO {

    private String username;

    private String password;

}
