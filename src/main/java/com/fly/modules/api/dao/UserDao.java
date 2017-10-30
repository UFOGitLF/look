package com.fly.modules.api.dao;

import com.fly.modules.api.entity.UserEntity;
import com.fly.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * Created by xinshidai on 17/10/27.
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity>{

    UserEntity queryByMobile(String mobile);
}
