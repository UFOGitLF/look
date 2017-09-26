package com.fly.modules.sys.controller;

import com.fly.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller 公共组件
 * Created by xinshidai on 17/9/18.
 */
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    //获取当前用户
    protected SysUserEntity getUser() {

        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    //获取用户ID
    protected Long getUserId() {
        logger.info("userID:{}", getUser().getUserId());
        return getUser().getUserId();
    }
}
