package com.fly.common.aspect;

import com.fly.common.annotation.SysLog;
import com.fly.common.utils.HttpContextUtils;
import com.fly.common.utils.IPUtils;
import com.fly.modules.sys.entity.SysLogEntity;
import com.fly.modules.sys.entity.SysUserEntity;
import com.fly.modules.sys.service.SysLogService;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志切面处理类
 * <p>
 * Created by xinshidai on 17/9/20.
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService logService;

    @Pointcut("@annotation(com.fly.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();

        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(joinPoint, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogEntity logEntity = new SysLogEntity();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            //注解上的描述
            logEntity.setOperation(sysLog.value());
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logEntity.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = new Gson().toJson(args[0]);
        logEntity.setParams(params);

        //获取request对象
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        logEntity.setIp(IPUtils.getIpAddr(request));

        //用户名
        String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
        logEntity.setUsername(username);

        logEntity.setTime(time);
        logEntity.setCreateDate(new Date());

        logService.save(logEntity);
    }

}
