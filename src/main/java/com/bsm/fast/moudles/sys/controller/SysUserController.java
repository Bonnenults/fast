package com.bsm.fast.moudles.sys.controller;

import com.bsm.fast.common.constant.Constants;
import com.bsm.fast.common.domain.R;
import com.bsm.fast.common.utils.PageUtils;
import com.bsm.fast.moudles.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/hello")
    public String hello(){
        return "hello world !!";
    }

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params){
        //只有超级管理员，才能查看所有管理员列表
        if(getUserId() != Constants.SUPER_ADMIN){
            params.put("createUserId", getUserId());
        }
        PageUtils page = sysUserService.listSysUser(params);

        return R.ok(page);
    }
}
