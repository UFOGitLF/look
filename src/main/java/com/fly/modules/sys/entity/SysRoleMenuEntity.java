package com.fly.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色对菜单对应关系
 * <p>
 * Created by xinshidai on 17/9/27.
 */
@Data
public class SysRoleMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
