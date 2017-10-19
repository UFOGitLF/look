package com.fly.modules.sys.service.impl;

import com.fly.common.utils.R;
import com.fly.modules.sys.dao.SysUserTokenDao;
import com.fly.modules.sys.entity.SysUserTokenEntity;
import com.fly.modules.sys.oauth2.TokenGenerator;
import com.fly.modules.sys.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysUserTokenServiceImpl implements SysUserTokenService {

    //12小时后过期(秒)
    private static final int EXPIRE = 3600 * 12;
    @Autowired
    private SysUserTokenDao tokenDao;

    @Override
    public SysUserTokenEntity queryByUserId(Long userId) {
        return tokenDao.queryByUserId(userId);
    }

    @Override
    public R createToken(Long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysUserTokenEntity tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            saveToken(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            updateToken(tokenEntity);
        }

        R r = R.ok().put("token", token).put("expire", EXPIRE);
        return r;
    }

    @Override
    public void saveToken(SysUserTokenEntity tokenEntity) {
        tokenDao.save(tokenEntity);
    }

    @Override
    public void updateToken(SysUserTokenEntity tokenEntity) {
        tokenDao.update(tokenEntity);
    }

    @Override
    public void logout(Long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        updateToken(tokenEntity);
    }
}
