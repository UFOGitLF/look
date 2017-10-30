package com.fly.modules.api.interceptor;

import com.fly.common.exception.RRException;
import com.fly.modules.api.annotation.AuthIgnore;
import com.fly.modules.api.entity.TokenEntity;
import com.fly.modules.api.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(TOKEN)验证
 * <p>
 * Created by xinshidai on 17/10/27.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    @Resource
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore authIgnore;
        if (handler instanceof HandlerMethod) {
            authIgnore = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        } else {
            return true;
        }

        //如果有@AuthIgnore注解,则不验证token
        if (authIgnore != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new RRException("token不能为空");
        }

        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new RRException("token失效，请重新登录");
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());

        return true;


    }
}
