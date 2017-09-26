package com.fly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 注册拦截器
 * Created by xinshidai on 17/9/20.
 */
@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {//http://127.0.0.1:8698/sys/login
        registry.addInterceptor(new HttpFilter()).addPathPatterns("/sys/**");  //对来自/renren-fast/sys/** 这个链接来的请求进行拦截
    }
}
