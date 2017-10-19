package com.fly.modules.job.utils;

import com.fly.common.utils.SpringContextUtils;
import com.fly.modules.job.entity.ScheduleJobEntity;
import com.fly.modules.job.entity.ScheduleJobLogEntity;
import com.fly.modules.job.service.ScheduleJobLogService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 定时任务
 * <p>
 * Created by xinshidai on 17/9/25.
 */
public class ScheduleJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jsonJob = jobExecutionContext.getMergedJobDataMap().getString(ScheduleJobEntity.JOB_PARAM_KEY);
        ScheduleJobEntity scheduleJobEntity = new Gson().fromJson(jsonJob, ScheduleJobEntity.class);

        //获取
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJObLogService");

        //数据库保存记录
        ScheduleJobLogEntity scheduleJobLogEntity = new ScheduleJobLogEntity();
        scheduleJobLogEntity.setJobId(scheduleJobEntity.getJobId());
        scheduleJobLogEntity.setBeanName(scheduleJobEntity.getBeanName());
        scheduleJobLogEntity.setMethodName(scheduleJobEntity.getMethodName());
        scheduleJobLogEntity.setParams(scheduleJobEntity.getParams());
        scheduleJobLogEntity.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            logger.info("任务准备执行，任务ID：" + scheduleJobEntity.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJobEntity.getBeanName(),
                    scheduleJobEntity.getMethodName(), scheduleJobEntity.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLogEntity.setTimes((int) times);
            //任务状态    0：成功    1：失败
            scheduleJobLogEntity.setStatus(0);

            logger.info("任务执行完毕，任务ID：" + scheduleJobEntity.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJobEntity.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLogEntity.setTimes((int) times);

            //任务状态    0：成功    1：失败
            scheduleJobLogEntity.setStatus(1);
            scheduleJobLogEntity.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.save(scheduleJobLogEntity);
        }
    }
}
