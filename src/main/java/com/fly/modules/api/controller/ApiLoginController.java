package com.fly.modules.api.controller;

import com.fly.common.utils.R;
import com.fly.common.validator.Assert;
import com.fly.modules.api.annotation.AuthIgnore;
import com.fly.modules.api.service.TokenService;
import com.fly.modules.api.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * API登录授权
 * <p>
 * Created by xinshidai on 17/10/27.
 */
@RestController
@RequestMapping("api")
public class ApiLoginController {
    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    /**
     * 登录
     *
     * @param mobile   手机号
     * @param password 密码
     * @return
     */
    @AuthIgnore
    @PostMapping(value = "login")
    public R login(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return R.ok(map);
    }

}
