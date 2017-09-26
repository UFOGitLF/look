package com.fly.modules.sys.controller;

import com.fly.common.annotation.SysLog;
import com.fly.common.utils.R;
import com.fly.common.validator.ValidatorUtils;
import com.fly.common.validator.group.AddGroup;
import com.fly.modules.sys.entity.SysUserEntity;
import com.fly.modules.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户
 * <p>
 * Created by xinshidai on 17/9/20.
 */
@RestController
@RequestMapping(value = "sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService userService;

    @SysLog("保存用户")
    @RequiresPermissions("sys:user:save")
    @PostMapping(value = "save")
    public R save(@RequestBody SysUserEntity userEntity) {
        ValidatorUtils.validateEntity(userEntity, AddGroup.class);
        userEntity.setCreateUserId(getUserId());
        userService.save(userEntity);

        return R.ok();
    }
}
