package com.bsm.fast.moudles.sys.controller;


import com.bsm.fast.moudles.sys.model.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

@Slf4j
public class BaseController {

    protected SysUser getSysUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getSysUser().getUserId();
    }
}
