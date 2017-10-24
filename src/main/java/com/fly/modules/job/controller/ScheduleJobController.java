package com.fly.modules.job.controller;

import com.fly.common.annotation.SysLog;
import com.fly.common.utils.PageData;
import com.fly.common.utils.Query;
import com.fly.common.utils.R;
import com.fly.common.validator.ValidatorUtils;
import com.fly.modules.job.entity.ScheduleJobEntity;
import com.fly.modules.job.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * Created by xinshidai on 17/10/19.
 */
@RestController
@RequestMapping("sys/schedule")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService jobService;

    @SysLog("保存定时任务")
    @RequestMapping("save")
    @RequiresPermissions("sys:schedule:save")
    public R save(@RequestBody ScheduleJobEntity jobEntity){
        ValidatorUtils.validateEntity(jobEntity);
        jobService.save(jobEntity);

        return R.ok();
    }

    @SysLog("修改定时任务")
    @RequestMapping("update")
    @RequiresPermissions("sys:schedule:update")
    public R update(@RequestBody ScheduleJobEntity jobEntity){
        ValidatorUtils.validateEntity(jobEntity);
        jobService.update(jobEntity);

        return R.ok();
    }

    @SysLog("删除定时任务")
    @RequestMapping("delete")
    @RequiresPermissions("sys:schedule:delete")
    public R delete(@RequestBody Long[] jobIds){
        jobService.deleteBatch(jobIds);

        return R.ok();
    }

    @RequestMapping("info/{jobId}")
    @RequiresPermissions("sys:schedule:info")
    public R info(@PathVariable Long jobId){
        ScheduleJobEntity schedule=jobService.queryObject(jobId);

        return R.ok().put("schedule",schedule);
    }

    @RequestMapping("list")
    @RequiresPermissions("sys:schedule:list")
    public R list(@RequestParam Map<String,Object> params){
        Query query=new Query(params);
        //查询列表数据
        List<ScheduleJobEntity> list=jobService.queryList(query);

        int total = jobService.queryTotal(query);

        PageData pageData=new PageData(list,total,query.getLimit(),query.getPage());

        return R.ok().put("page",pageData);
    }

    @SysLog("暂停定时任务")
    @RequestMapping("pause")
    @RequiresPermissions("sys:schedule:pause")
    public R pause(@RequestBody Long[] jobIds){
        jobService.pause(jobIds);

        return R.ok();
    }

    @SysLog("恢复定时任务")
    @RequestMapping("resume")
    @RequiresPermissions("sys:schedule:resume")
    public R resume(@RequestBody Long[] jobIds){
        jobService.resume(jobIds);

        return R.ok();
    }

    @SysLog("立即执行任务")
    @RequestMapping("run")
    @RequiresPermissions("sys:schedule:run")
    public R run(@RequestBody Long[] jobIds){
        jobService.run(jobIds);

        return R.ok();
    }
}
