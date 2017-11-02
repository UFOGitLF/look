package com.fly.common.aspect;

import com.fly.common.exception.RRException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Redis 切面处理类
 * <p>
 * Created by xinshidai on 17/9/19.
 */
@Aspect
@Configuration
public class RedisAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.redis.open: false}")
    private boolean open;

    @Pointcut("execution(* com.fly.common.utils.RedisUtils.*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        if (open) {
            try {
                result = joinPoint.proceed();
            } catch (Exception e) {
                logger.error("Redis error", e);
                throw new RRException("Redis 服务器异常");
            }
        }
        return result;
    }
}
