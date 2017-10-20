package com.fly.modules.oss.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 * Created by xinshidai on 17/10/19.
 */
@Data
public class SysOssEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String url;
    private Date createDate;

}
