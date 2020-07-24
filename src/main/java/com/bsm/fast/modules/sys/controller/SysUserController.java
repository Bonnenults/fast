package com.bsm.fast.modules.sys.controller;

import com.bsm.fast.common.constant.Constants;
import com.bsm.fast.common.domain.R;
import com.bsm.fast.common.utils.PageUtils;
import com.bsm.fast.common.utils.ShiroUtils;
import com.bsm.fast.common.validator.Assert;
import com.bsm.fast.modules.sys.model.entity.SysUser;
import com.bsm.fast.modules.sys.service.SysUserRoleService;
import com.bsm.fast.modules.sys.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author limingxin
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world !!";
    }

    /**
     * 所有用户列表
     */
    @ApiOperation("所有用户列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() != Constants.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }
        PageUtils page = sysUserService.listSysUser(params);

        return R.ok(page);
    }

    /**
     * 获取登陆用户信息
     *
     * @return
     */
    @ApiOperation("获取登陆用户信息")
    @GetMapping("/info")
    public R getUserInfo() {
        return R.ok(getSysUser());
    }


    /**
     * 修改密码
     *
     * @param password    老密码
     * @param newPassword 新密码
     * @return
     */
    @ApiOperation("修改密码")
    @PostMapping("/password")
    public R changePassword(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不能为空");

        //sha256加密
        password = ShiroUtils.sha256(password, getSysUser().getSalt());
        newPassword = ShiroUtils.sha256(newPassword, getSysUser().getSalt());

        boolean flag = sysUserService.changePassword(getUserId(), password, newPassword);
        if(!flag){
            return R.error("原密码不正确");
        }
        return R.ok();
    }

    /**
     * 根据ID获取用户信息
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据ID获取用户信息")
    @GetMapping("/info/{userId}")
    public R getUserInfoById(@PathVariable("userId") Long userId){
        SysUser user = sysUserService.getById(userId);
        //获取用户所属的角色列表
        List<Long> roleIds = sysUserRoleService.getRoleIdList(userId);
        user.setRoleIdList(roleIds);

        return R.ok(user);
    }
}
