package com.fly.modules.sys.service;

import com.fly.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 * <p>
 * Created by xinshidai on 17/9/21.
 */
public interface SysLogService {
    /**
     * 保存系统日志
     */
    void save(SysLogEntity logEntity);
}
