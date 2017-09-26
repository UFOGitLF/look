package com.fly.modules.sys.dao;

import com.fly.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 * Created by xinshidai on 17/9/20.
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
