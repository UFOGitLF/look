package com.fly.modules.job.service.impl;

import com.fly.common.utils.Constant;
import com.fly.modules.job.dao.ScheduleJobDao;
import com.fly.modules.job.entity.ScheduleJobEntity;
import com.fly.modules.job.service.ScheduleJobService;
import com.fly.modules.job.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * Created by xinshidai on 17/10/19.
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService{

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleJobDao jobDao;

    @Override
    public ScheduleJobEntity queryObject(Long jobId) {
        return jobDao.queryObject(jobId);
    }

    @Override
    public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
        return jobDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return jobDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(ScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        jobDao.save(scheduleJob);

        ScheduleUtils.createScheduleJob(scheduler,scheduleJob);
    }

    @Override
    @Transactional
    public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler,scheduleJob);

        jobDao.update(scheduleJob);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId:jobIds){
            ScheduleUtils.deleteScheduleJob(scheduler,jobId);
        }
        //删除数据
        jobDao.deleteBatch(jobIds);
    }

    @Override
    public int updateBatch(Long[] jobIds, int status) {
        Map<String,Object> map=new HashMap<>();
        map.put("list",jobIds);
        map.put("status",status);
        return jobDao.update(map);
    }

    @Override
    @Transactional
    public void run(Long[] jobIds) {
        for (Long jobId:jobIds){
            ScheduleUtils.run(scheduler,queryObject(jobId));
        }
    }

    @Override
    @Transactional
    public void pause(Long[] jobIds) {
        for (Long jobId:jobIds){
            ScheduleUtils.pauseJob(scheduler,jobId);
        }
        updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional
    public void resume(Long[] jobIds) {
        for (Long jobId:jobIds){
            ScheduleUtils.resumeJob(scheduler,jobId);
        }
        updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }
}
