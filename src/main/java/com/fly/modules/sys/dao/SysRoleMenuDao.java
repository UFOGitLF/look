package com.fly.modules.sys.dao;

import com.fly.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色对菜单对应关系
 * <p>
 * Created by xinshidai on 17/9/27.
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
    /**
     * 根据角色ID,获取菜单ID列表
     *
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdList(Long roleId);
}
