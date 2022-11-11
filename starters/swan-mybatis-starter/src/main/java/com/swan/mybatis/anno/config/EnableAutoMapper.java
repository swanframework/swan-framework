package com.swan.mybatis.anno.config;

import com.swan.mybatis.core.BaseMapperRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(BaseMapperRegister.class)
public @interface EnableAutoMapper {


}