package com.fly.modules.job.dao;

import com.fly.modules.job.entity.ScheduleJobEntity;
import com.fly.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务
 * <p>
 * Created by xinshidai on 17/10/19.
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {

}
