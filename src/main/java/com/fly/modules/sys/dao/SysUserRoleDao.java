package com.fly.modules.sys.dao;

import com.fly.modules.sys.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系
 * <p>
 * Created by xinshidai on 17/9/20.
 */
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
    List<Long> queryRoleIdList(Long userId);
}
