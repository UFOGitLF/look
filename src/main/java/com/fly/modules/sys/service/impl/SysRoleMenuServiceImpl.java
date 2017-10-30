package com.fly.modules.sys.service.impl;

import com.fly.modules.sys.dao.SysRoleMenuDao;
import com.fly.modules.sys.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * <p>
 * Created by xinshidai on 17/9/27.
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuDao roleMenuDao;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        roleMenuDao.delete(roleId);

        if (menuIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        roleMenuDao.save(map);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return roleMenuDao.queryMenuIdList(roleId);
    }
}
