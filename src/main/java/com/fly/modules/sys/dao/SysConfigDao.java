package com.fly.modules.sys.dao;

import com.fly.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置
 *
 * Created by xinshidai on 17/9/25.
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity>{
    /**
     * 根据key，查询value
     */
    SysConfigEntity queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);
}
