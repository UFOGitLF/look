package com.fly.modules.job.dao;

import com.fly.modules.job.entity.ScheduleJobLogEntity;
import com.fly.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 * <p>
 * Created by xinshidai on 17/10/23.
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
}
