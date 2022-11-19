package com.swan.mybatis.config;

import com.swan.mybatis.core.AutoMapperInitializer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/** 是否开启自动 mapper 功能*/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoMapperInitializer.class)
public @interface EnableAutoMapper {


}