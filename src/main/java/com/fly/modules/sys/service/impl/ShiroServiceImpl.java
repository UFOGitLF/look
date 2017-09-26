package com.fly.modules.sys.service.impl;

import com.fly.common.utils.Constant;
import com.fly.modules.sys.dao.SysMenuDao;
import com.fly.modules.sys.dao.SysUserDao;
import com.fly.modules.sys.dao.SysUserTokenDao;
import com.fly.modules.sys.entity.SysMenuEntity;
import com.fly.modules.sys.entity.SysUserEntity;
import com.fly.modules.sys.entity.SysUserTokenEntity;
import com.fly.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao menuDao;
    @Autowired
    private SysUserDao userDao;
    @Autowired
    private SysUserTokenDao userTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
        //系统管理员
        if (userId == Constant.SUPER_ADMIN) {
            List<SysMenuEntity> menuEntities = menuDao.queryList(new HashMap<>());
            permsList = new ArrayList<>(menuEntities.size());
            for (SysMenuEntity entity : menuEntities) {
                permsList.add(entity.getPerms());
            }
        } else {
            permsList = userDao.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return userTokenDao.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return userDao.queryObject(userId);
    }
}
