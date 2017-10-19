package com.fly.modules.sys.service;

import java.util.List;

/**
 * 用户与角色对应关系
 * Created by xinshidai on 17/9/20.
 */
public interface SysUserRoleService {
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID,获取角色ID列表
     * @param userId
     * @return
     */
    List<Long> queryRoleIdList(Long userId);

    void delete(Long userId);
}
