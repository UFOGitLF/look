package com.fly.modules.sys.service;

import java.util.List;

/**
 * 角色
 * <p>
 * Created by xinshidai on 17/9/20.
 */
public interface SysRoleService {
    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
