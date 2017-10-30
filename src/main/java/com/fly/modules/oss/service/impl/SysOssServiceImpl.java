package com.fly.modules.oss.service.impl;

import com.fly.modules.oss.dao.SysOssDao;
import com.fly.modules.oss.entity.SysOssEntity;
import com.fly.modules.oss.service.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * <p>
 * Created by xinshidai on 17/10/19.
 */
@Service
public class SysOssServiceImpl implements SysOssService {
    @Autowired
    private SysOssDao ossDao;

    @Override
    public SysOssEntity queryObject(Long id) {
        return ossDao.queryObject(id);
    }

    @Override
    public List<SysOssEntity> queryList(Map<String, Object> map) {
        return ossDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return ossDao.queryTotal(map);
    }

    @Override
    public void save(SysOssEntity sysOss) {
        ossDao.save(sysOss);
    }

    @Override
    public void update(SysOssEntity sysOss) {
        ossDao.update(sysOss);
    }

    @Override
    public void delete(Long id) {
        ossDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        ossDao.deleteBatch(ids);
    }
}
