package com.fly.modules.sys.controller;

import com.fly.common.utils.R;
import com.fly.common.utils.ResultCodeConstants;
import com.fly.modules.sys.entity.SysUserEntity;
import com.fly.modules.sys.service.SysUserService;
import com.fly.modules.sys.service.SysUserTokenService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 登录相关
 * Created by xinshidai on 17/9/18.
 */
@RestController
@RequestMapping(value = "sys")
public class SysLoginController extends BaseController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserTokenService userTokenService;

    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @CrossOrigin(origins = {"http://127.0.0.1:8888", "http://localhost:8888"})
    @PostMapping(value = "login")
    public Map<String, Object> login(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password) {
        //用户信息
        SysUserEntity user = userService.queryByUserName(username);

        //账号或密码错误
        if (null == user || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return R.error(ResultCodeConstants.USERNAME_OR_PASSWORD_WRONG);
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return R.error(ResultCodeConstants.ACCOUNT_LOCKED);
        }

        //生成token,并保存到数据库
        R r = userTokenService.createToken(user.getUserId());
        return r;
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping(value = "logout")
    public R logout() {
        userTokenService.logout(getUserId());
        return R.ok();
    }


}
