package com.fly.modules.sys.service;

import com.fly.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 查询用户列表
     */
    List<SysUserEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 用户修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    int updatePassword(Long userId, String password, String newPassword);

    /**
     * 根据用户ID查询用户
     *
     * @param userId
     */
    SysUserEntity queryObject(Long userId);

    /**
     * 修改用户
     */
    void update(SysUserEntity user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);
}
