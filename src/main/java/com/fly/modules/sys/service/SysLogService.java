package com.fly.modules.sys.service;

import com.fly.modules.sys.entity.SysLogEntity;

import java.util.List;
import java.util.Map;

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

    SysLogEntity queryObject(Long id);

    List<SysLogEntity> queryList(Map<String, Object> params);

    int queryTotal(Map<String, Object> map);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
