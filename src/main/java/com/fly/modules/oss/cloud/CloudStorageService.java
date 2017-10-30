package com.fly.modules.oss.cloud;

import com.fly.common.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 云存储(目前支持七牛,可扩展)
 * <p>
 * Created by xinshidai on 17/10/19.
 */
public abstract class CloudStorageService {
    /**
     * 云存储配置信息
     **/
    CloudStorageConfig config;

    /**
     * 获取文件路径
     *
     * @param prefix 路径前缀
     */
    public String getPath(String prefix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "/" + uuid;

        if (StringUtils.isNotBlank(prefix)) {
            path = prefix + "/" + path;
        }
        return path;
    }

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件上传路径,包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @return 返回http地址
     */
    public abstract String upload(byte[] data);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径,包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream);
}
