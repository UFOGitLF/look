package com.fly.modules.oss.service;

import com.fly.modules.oss.entity.SysOssEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * Created by xinshidai on 17/10/19.
 */
public interface SysOssService {
    SysOssEntity queryObject(Long id);

    List<SysOssEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysOssEntity sysOss);

    void update(SysOssEntity sysOss);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
