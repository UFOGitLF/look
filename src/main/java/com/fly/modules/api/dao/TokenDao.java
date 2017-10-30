package com.fly.modules.api.dao;

import com.fly.modules.api.entity.TokenEntity;
import com.fly.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * Token
 * <p>
 * Created by xinshidai on 17/10/27.
 */
@Mapper
public interface TokenDao extends BaseDao<TokenEntity> {
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
}
