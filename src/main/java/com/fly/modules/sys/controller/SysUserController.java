package com.fly.modules.sys.controller;

import com.fly.common.annotation.SysLog;
import com.fly.common.utils.Constant;
import com.fly.common.utils.PageData;
import com.fly.common.utils.Query;
import com.fly.common.utils.R;
import com.fly.common.validator.Assert;
import com.fly.common.validator.ValidatorUtils;
import com.fly.common.validator.group.AddGroup;
import com.fly.common.validator.group.UpdateGroup;
import com.fly.modules.sys.entity.SysUserEntity;
import com.fly.modules.sys.service.SysUserRoleService;
import com.fly.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private SysUserRoleService userRoleService;

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequiresPermissions("sys:user:save")
    @PostMapping(value = "save")
    public R save(@RequestBody SysUserEntity userEntity) {
        ValidatorUtils.validateEntity(userEntity, AddGroup.class);
        userEntity.setCreateUserId(getUserId());
        userService.save(userEntity);

        return R.ok();
    }

    /**
     * 所有用户列表
     */
    @RequestMapping("list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String,Object> params){
        //只有超级管理员,才能查看所有管理员列表
        if (getUserId() != Constant.SUPER_ADMIN){
            params.put("createUserId",getUserId());
        }
        //查询列表数据
        Query query=new Query(params);
        List<SysUserEntity> userList=userService.queryList(params);
        int total=userService.queryTotal(query);

        PageData pageData=new PageData(userList,total,query.getLimit(),query.getPage());

        return R.ok().put("page",pageData);
    }
    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info(){
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("password")
    public R changePassword(String password,String newPassword){
        Assert.isBlank(newPassword,"新密码不能为空");

        //sha256加密
        password=new Sha256Hash(password,getUser().getSalt()).toHex();

        newPassword=new Sha256Hash(newPassword,getUser().getSalt()).toHex();

        //更新密码
        int count=userService.updatePassword(getUserId(),password,newPassword);
        if (count == 0){
            return R.error("原密码不正确");
        }
        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId){
        SysUserEntity user=userService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList=userRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user",user);
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserEntity user){
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(getUserId());
        userService.update(user);

        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Long[] userIds){
        if(ArrayUtils.contains(userIds, 1L)){
            return R.error("系统管理员不能删除");
        }

        if(ArrayUtils.contains(userIds, getUserId())){
            return R.error("当前用户不能删除");
        }

        userService.deleteBatch(userIds);

        return R.ok();
    }


}
