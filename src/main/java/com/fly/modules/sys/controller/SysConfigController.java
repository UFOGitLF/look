package com.fly.modules.sys.controller;

import com.fly.common.annotation.SysLog;
import com.fly.common.utils.PageData;
import com.fly.common.utils.Query;
import com.fly.common.utils.R;
import com.fly.common.validator.ValidatorUtils;
import com.fly.modules.sys.entity.SysConfigEntity;
import com.fly.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统参数配置
 *
 * Created by xinshidai on 17/10/18.
 */
@RestController
@RequestMapping(value = "sys/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有配置列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SysConfigEntity> configList = sysConfigService.queryList(query);
        int total = sysConfigService.queryTotal(query);

        PageData pageData = new PageData(configList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageData);
    }


    /**
     * 配置信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public R info(@PathVariable("id") Long id){
        SysConfigEntity config = sysConfigService.queryObject(id);

        return R.ok().put("config", config);
    }

    /**
     * 保存配置
     */
    @SysLog("保存配置")
    @RequestMapping("/save")
    @RequiresPermissions("sys:config:save")
    public R save(@RequestBody SysConfigEntity config){
        ValidatorUtils.validateEntity(config);

        sysConfigService.save(config);

        return R.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @RequestMapping("/update")
    @RequiresPermissions("sys:config:update")
    public R update(@RequestBody SysConfigEntity config){
        ValidatorUtils.validateEntity(config);

        sysConfigService.update(config);

        return R.ok();
    }

    /**
     * 删除配置
     */
    @SysLog("删除配置")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public R delete(@RequestBody Long[] ids){
        sysConfigService.deleteBatch(ids);

        return R.ok();
    }


}
