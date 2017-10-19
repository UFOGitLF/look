package com.fly.modules.sys.service.impl;

import com.fly.modules.sys.dao.SysLogDao;
import com.fly.modules.sys.entity.SysLogEntity;
import com.fly.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public SysLogEntity queryObject(Long id) {
        return logDao.queryObject(id);
    }

    @Override
    public List<SysLogEntity> queryList(Map<String, Object> params) {
        return logDao.queryList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return logDao.queryTotal(map);
    }

    @Override
    public void delete(Long id) {
        logDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        logDao.deleteBatch(ids);
    }
}
