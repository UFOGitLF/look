package com.fly.dynamicdatasource.annotation;

import java.lang.annotation.*;

/**
 * @Author : liufei on 2018/4/8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
