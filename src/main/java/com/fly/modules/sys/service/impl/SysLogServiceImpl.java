package com.fly.modules.sys.service.impl;

import com.fly.modules.sys.dao.SysLogDao;
import com.fly.modules.sys.entity.SysLogEntity;
import com.fly.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统日志
 * <p>
 * Created by xinshidai on 17/9/21.
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao logDao;

    @Override
    public void save(SysLogEntity logEntity) {
        logDao.save(logEntity);
    }
}
