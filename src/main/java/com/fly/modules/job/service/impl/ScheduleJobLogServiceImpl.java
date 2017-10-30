package com.fly.modules.job.service.impl;

import com.fly.modules.job.dao.ScheduleJobLogDao;
import com.fly.modules.job.entity.ScheduleJobLogEntity;
import com.fly.modules.job.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * <p>
 * Created by xinshidai on 17/10/23.
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
    @Autowired
    private ScheduleJobLogDao jobLogDao;

    @Override
    public ScheduleJobLogEntity queryObject(Long jobId) {
        return jobLogDao.queryObject(jobId);
    }

    @Override
    public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
        return jobLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return jobLogDao.queryTotal(map);
    }

    @Override
    public void save(ScheduleJobLogEntity log) {
        jobLogDao.save(log);
    }
}
