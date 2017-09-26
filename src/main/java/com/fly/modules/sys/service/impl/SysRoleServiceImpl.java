package com.fly.modules.sys.service.impl;

import com.fly.modules.sys.dao.SysRoleDao;
import com.fly.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色
 * <p>
 * Created by xinshidai on 17/9/20.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao roleDao;


    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return roleDao.queryRoleIdList(createUserId);
    }
}
