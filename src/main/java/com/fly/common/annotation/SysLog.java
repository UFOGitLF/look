package com.fly.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * <p>
 * Created by xinshidai on 17/9/20.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";

}
