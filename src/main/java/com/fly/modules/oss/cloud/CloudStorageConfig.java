package com.fly.modules.oss.cloud;

import com.fly.common.validator.group.QiniuGroup;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

/**
 * 云存储配置信息
 * <p>
 * Created by xinshidai on 17/10/19.
 */
@Data
public class CloudStorageConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    //类型,此处可以扩展,只需要扩充实体类就ok.1--七牛  2--xxx  3--xxx
    @Range(min = 1, max = 1, message = "类型错误")
    private Integer type;

    //七牛绑定的域名
    @NotBlank(message = "七牛绑定的域名不能为空", groups = QiniuGroup.class)
    @URL(message = "七牛域名格式不正确", groups = QiniuGroup.class)
    private String qiniuDomain;

    //七牛路径前缀
    private String qiniuPrefix;

    //七牛ACCESS_KEY
    @NotBlank(message = "七牛AccessKey不能为空", groups = QiniuGroup.class)
    private String qiniuAccessKey;

    //七牛SECRET_KEY
    @NotBlank(message = "七牛SecretKey不能为空", groups = QiniuGroup.class)
    private String qiniuSecretKey;

    //七牛存储空间名
    @NotBlank(message = "七牛存储空间名不能为空", groups = QiniuGroup.class)
    private String qiniuBucketName;


}
