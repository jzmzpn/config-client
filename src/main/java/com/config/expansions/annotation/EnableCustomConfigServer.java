package com.config.expansions.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.config.expansions.CustomConfigServerConfiguration;
import org.springframework.context.annotation.Import;

/*
*
* 自定义配置中心
* 通过该注解启用自定义配置，目前只实现了redis，可以另外扩展mongodb，或者其他的方式
* */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CustomConfigServerConfiguration.class})
public @interface EnableCustomConfigServer {
}