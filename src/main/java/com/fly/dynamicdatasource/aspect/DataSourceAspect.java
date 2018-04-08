package com.fly.dynamicdatasource.aspect;

import com.fly.dynamicdatasource.DataSourceContext;
import com.fly.dynamicdatasource.DynamicDataSource;
import com.fly.dynamicdatasource.annotation.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 * @Author : liufei on 2018/4/8
 */
@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    protected static final Logger log = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.fly.dynamicdatasource.annotation.DataSource)")
    public void dataSourcePointCut(){

    }
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);

        if (dataSource == null) {
            DynamicDataSource.setDataSource(DataSourceContext.FIRST);
            log.debug("Set datasource is {}",DataSourceContext.FIRST);
        }else {
            DynamicDataSource.setDataSource(dataSource.name());
            log.debug("Set datasource is {}",dataSource.name());
        }

        try {
            return joinPoint.proceed();
        }finally {
            DynamicDataSource.clearDataSource();
            log.debug("clean datasource");
        }
    }
}
