package com.fly.modules.sys.service;

import com.fly.modules.sys.entity.SysUserEntity;

import java.util.List;

/**
 * 系统用户
 * Created by xinshidai on 17/9/18.
 */
public interface SysUserService {
    /**
     * 根据用户名查找用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 保存
     */
    void save(SysUserEntity userEntity);

    List<Long> queryAllMenuId(Long userId);
}
