package com.fly.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 * Created by xinshidai on 17/9/20.
 */
@Data
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long roleId;

}
