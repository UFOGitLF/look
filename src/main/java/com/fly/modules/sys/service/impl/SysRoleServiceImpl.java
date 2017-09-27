package com.fly.modules.sys.service.impl;

import com.fly.common.exception.RRException;
import com.fly.common.utils.Constant;
import com.fly.modules.sys.dao.SysRoleDao;
import com.fly.modules.sys.entity.SysRoleEntity;
import com.fly.modules.sys.service.SysRoleMenuService;
import com.fly.modules.sys.service.SysRoleService;
import com.fly.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色
 * <p>
 * Created by xinshidai on 17/9/20.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao roleDao;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return roleDao.queryRoleIdList(createUserId);
    }

    @Override
    public List<SysRoleEntity> queryList(Map<String, Object> map) {
        return roleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return roleDao.queryTotal(map);
    }

    @Override
    public SysRoleEntity queryObject(Long roleId) {
        return roleDao.queryObject(roleId);
    }

    @Override
    @Transactional
    public void save(SysRoleEntity role) {
        role.setCreateTime(new Date());
        roleDao.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

    }

    @Override
    @Transactional
    public void update(SysRoleEntity role) {
        roleDao.update(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        roleDao.deleteBatch(roleIds);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(SysRoleEntity role){
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if(role.getCreateUserId() == Constant.SUPER_ADMIN){
            return ;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = userService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if(!menuIdList.containsAll(role.getMenuIdList())){
            throw new RRException("新增角色的权限，已超出你的权限范围");
        }
    }
}
