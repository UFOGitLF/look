package com.fly.modules.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * <p>
 * Created by xinshidai on 17/10/27.
 */
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码(为了安全,password被transient修饰,不被序列化)
     */
    transient private String password;
    /**
     * 创建时间
     */
    private Date createTime;

}
