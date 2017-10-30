package com.fly.modules.oss.controller;

import com.fly.common.utils.*;
import com.fly.common.validator.ValidatorUtils;
import com.fly.common.validator.group.QiniuGroup;
import com.fly.modules.oss.cloud.CloudStorageConfig;
import com.fly.modules.oss.cloud.OSSFactory;
import com.fly.modules.oss.entity.SysOssEntity;
import com.fly.modules.oss.service.SysOssService;
import com.fly.modules.sys.service.SysConfigService;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * <p>
 * Created by xinshidai on 17/10/19.
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
    private static final String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

    @Autowired
    private SysOssService ossService;

    @Autowired
    private SysConfigService configService;

    /**
     * 列表
     */
    @RequestMapping("list")
    @RequiresPermissions("sys:oss:all")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<SysOssEntity> ossList = ossService.queryList(query);

        int total = ossService.queryTotal(query);

        PageData pageData = new PageData(ossList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageData);
    }

    /**
     * 云存储配置信息
     */
    @RequestMapping("config")
    @RequiresPermissions("sys:oss:all")
    public R config() {
        CloudStorageConfig config = configService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }

    /**
     * 保存云存储配置信息
     */
    @RequestMapping("saveConfig")
    @RequiresPermissions("sys:oss:all")
    public R saveConfig(@RequestBody CloudStorageConfig config) {
        //校验合法性
        ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //检验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        }
        configService.updateValueByKey(KEY, new Gson().toJson(config));
        return R.ok();
    }

    /**
     * 上传文件
     */
    @RequestMapping("upload")
    @RequiresPermissions("sys:oss:all")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error(ResultCodeConstants.FILE_EMPTY_ERROR);
        }
        //上传文件
        String url = OSSFactory.build().upload(file.getBytes());

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        ossService.save(ossEntity);

        return R.ok().put("url", url);
    }

    /**
     * 删除
     */
    @RequestMapping("delete")
    @RequiresPermissions("sys:oss:all")
    public R delete(@RequestBody Long[] ids) {
        ossService.deleteBatch(ids);
        return R.ok();
    }

}
