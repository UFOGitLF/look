package com.fly.modules.api.annotation;

import java.lang.annotation.*;

/**
 * API接口,忽略TOKEN验证
 * <p>
 * Created by xinshidai on 17/10/27.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {
}
