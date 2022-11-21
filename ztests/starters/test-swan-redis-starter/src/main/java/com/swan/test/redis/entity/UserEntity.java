package com.swan.test.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zongf
 * @date 2021-05-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {

    private String username;

    private String password;

}
