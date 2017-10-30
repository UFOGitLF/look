package com.fly.modules.oss.cloud;

import com.fly.common.utils.ConfigConstant;
import com.fly.common.utils.Constant;
import com.fly.common.utils.SpringContextUtils;
import com.fly.modules.sys.service.SysConfigService;

/**
 * 云存储upload-factory
 * <p>
 * Created by xinshidai on 17/10/19.
 */
public final class OSSFactory {
    private static SysConfigService configService;

    static {
        OSSFactory.configService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = configService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else {
            return null;
        }
    }
}
