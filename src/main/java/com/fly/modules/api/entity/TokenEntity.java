package com.fly.modules.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户Token
 * <p>
 * Created by xinshidai on 17/10/27.
 */
@Data
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * token
     */
    private String token;
    /**
     * 失效时间
     */
    private Date expireTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
