package com.fly.modules.sys.service.impl;

import com.fly.common.exception.RRException;
import com.fly.common.utils.Constant;
import com.fly.modules.sys.dao.SysUserDao;
import com.fly.modules.sys.entity.SysUserEntity;
import com.fly.modules.sys.service.SysRoleService;
import com.fly.modules.sys.service.SysUserRoleService;
import com.fly.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    @Transactional
    public void save(SysUserEntity userEntity) {
        userEntity.setCreateTime(new Date());

        String salt = RandomStringUtils.randomAlphanumeric(20);
        userEntity.setPassword(new Sha256Hash(userEntity.getPassword(), salt).toHex());
        userEntity.setSalt(salt);
        sysUserDao.save(userEntity);

        //检查是否越权
        checkRole(userEntity);

        //保存用户与角色对应关系
        userRoleService.saveOrUpdate(userEntity.getUserId(), userEntity.getRoleIdList());
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("password",password);
        map.put("newPassword",newPassword);

        return sysUserDao.updatePassword(map);
    }

    @Override
    public SysUserEntity queryObject(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        sysUserDao.update(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userIds) {
        sysUserDao.deleteBatch(userIds);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity userEntity) {
        if (userEntity.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }
        //查询用户创建的角色列表
        List<Long> roleIdList = roleService.queryRoleIdList(userEntity.getCreateUserId());

        //为用户初始化角色
        if (CollectionUtils.isEmpty(userEntity.getRoleIdList())) {
            throw new RRException("请为用户绑定至少一个角色");
        }
        //判断是否越权
        if (!roleIdList.containsAll(userEntity.getRoleIdList())) {
            throw new RRException("新增用户所选角色,不是本人创建");
        }
    }
}
