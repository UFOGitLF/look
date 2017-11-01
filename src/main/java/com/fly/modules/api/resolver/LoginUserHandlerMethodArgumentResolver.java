package com.fly.modules.api.resolver;

import com.fly.modules.api.annotation.LoginUser;
import com.fly.modules.api.entity.UserEntity;
import com.fly.modules.api.interceptor.AuthorizationInterceptor;
import com.fly.modules.api.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 * <p>
 * Created by xinshidai on 17/10/27.
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(UserEntity.class) && methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //获取用户ID
        Object object = nativeWebRequest.getAttribute(AuthorizationInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }
        UserEntity userEntity = userService.queryObject((Long) object);

        return userEntity;
    }
}
