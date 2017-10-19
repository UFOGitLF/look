package com.fly.modules.sys.service;

import com.fly.common.utils.R;
import com.fly.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户token
 * Created by xinshidai on 17/9/18.
 */
public interface SysUserTokenService {

    SysUserTokenEntity queryByUserId(Long userId);

    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    R createToken(Long userId);

    /**
     * 保存token
     *
     * @param tokenEntity
     */
    void saveToken(SysUserTokenEntity tokenEntity);

    /**
     * 更新token
     *
     * @param tokenEntity
     */
    void updateToken(SysUserTokenEntity tokenEntity);

    /**
     * 退出
     *
     * @param userId
     */
    void logout(Long userId);
}
