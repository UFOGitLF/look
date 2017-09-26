package com.fly.modules.sys.service;

import com.fly.modules.sys.entity.SysUserEntity;
import com.fly.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro 相关接口
 * <p>
 * Created by xinshidai on 17/9/21.
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 查询token
     */
    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID,查询用户
     */
    SysUserEntity queryUser(Long userId);
}
