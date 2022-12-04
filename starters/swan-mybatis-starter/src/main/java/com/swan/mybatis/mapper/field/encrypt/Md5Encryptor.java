package com.swan.mybatis.mapper.field.encrypt;

import cn.hutool.crypto.SecureUtil;

/**
 * @author zongf
 * @since 2022-12-04
 **/
public class Md5Encryptor implements IEncryptor{

    @Override
    public String encrypt(String plain) {
        return SecureUtil.md5(plain);
    }

}
