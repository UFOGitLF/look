package com.fly.modules.sys.controller;


import com.fly.common.utils.PageData;
import com.fly.common.utils.Query;
import com.fly.common.utils.R;
import com.fly.modules.sys.entity.SysLogEntity;
import com.fly.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * <p>
 * Created by xinshidai on 17/9/26.
 */
@RestController
@RequestMapping(value = "/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService logService;

    @RequestMapping(value = "/list")
    @RequiresPermissions("sys:log:list")
    public R logList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysLogEntity> sysLogList = logService.queryList(params);
        int total = logService.queryTotal(query);

        PageData pageData = new PageData(sysLogList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageData);
    }
}
