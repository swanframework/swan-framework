package com.swan.mybatis.config;

import com.swan.mybatis.core.BaseMapperInitializer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/** 是否开启自动 mapper 功能*/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(BaseMapperInitializer.class)
public @interface EnableAutoMapper {


}