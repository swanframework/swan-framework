package com.swan.mybatis.config;

import com.swan.mybatis.core.BaseMapperRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/** 是否开启自动 mapper 功能*/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(BaseMapperRegister.class)
public @interface EnableAutoMapper {


}