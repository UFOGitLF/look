package com.fly.modules.sys.service;

import com.fly.common.utils.Query;
import com.fly.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

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

    List<SysRoleEntity> queryList(Map<String,Object> map);

    int queryTotal(Map<String,Object> map);

    SysRoleEntity queryObject(Long roleId);

    void save(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(Long[] roleIds);
}
