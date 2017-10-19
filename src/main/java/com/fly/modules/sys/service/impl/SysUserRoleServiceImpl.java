package com.fly.modules.sys.service.impl;

import com.fly.modules.sys.dao.SysUserRoleDao;
import com.fly.modules.sys.service.SysUserRoleService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * Created by xinshidai on 17/9/20.
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao userRoleDao;

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return;
        }
        //先删除用户与角色关系
        userRoleDao.delete(userId);

        //保存用户与角色关系
        Map<String, Object> map = new HashedMap();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);

        userRoleDao.save(map);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return userRoleDao.queryRoleIdList(userId);
    }

    @Override
    public void delete(Long userId) {
        userRoleDao.delete(userId);
    }
}
