package com.fly.modules.sys.entity;

import com.fly.common.validator.group.AddGroup;
import com.fly.common.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 * Created by xsd on 17/9/18.
 */
@Data
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Length(min = 3,max = 6,message = "用户名必须在3-6个字符之间",groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    //注：(?!xxxx) 是正则表达式的负向零宽断言一种形式，标识预该位置后不是xxxx字符。
    @Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$", message="密码必须是6~15位数字和字母的组合",groups = {AddGroup.class, UpdateGroup.class})
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message="手机号格式不正确",groups = {AddGroup.class, UpdateGroup.class})
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

}
