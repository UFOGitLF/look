package com.fly.modules.job.controller;

import com.fly.common.utils.PageData;
import com.fly.common.utils.Query;
import com.fly.common.utils.R;
import com.fly.modules.job.entity.ScheduleJobLogEntity;
import com.fly.modules.job.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * <p>
 * Created by xinshidai on 17/10/23.
 */
@RestController
@RequestMapping("sys/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService jobLogService;

    /**
     * 定时任务日志列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:log")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ScheduleJobLogEntity> jobList = jobLogService.queryList(query);
        int total = jobLogService.queryTotal(query);

        PageData pageData = new PageData(jobList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageData);
    }

    /**
     * 定时任务日志信息
     */
    @RequestMapping("/info/{logId}")
    public R info(@PathVariable("logId") Long logId) {
        ScheduleJobLogEntity log = jobLogService.queryObject(logId);

        return R.ok().put("log", log);
    }
}
