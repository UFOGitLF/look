package com.fly.modules.sys.dao;

import com.fly.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户TOKEN
 * Created by xinshidai on 17/9/18.
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {
    SysUserTokenEntity queryByUserId(Long userId);

    SysUserTokenEntity queryByToken(String token);
}
