package com.bsm.fast.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bsm.fast.common.utils.PageUtils;
import com.bsm.fast.modules.sys.model.entity.SysUser;

import java.util.Map;

public interface SysUserService extends IService<SysUser> {

    /**
     * 所有用户列表
     */
    PageUtils listSysUser(Map<String, Object> params);

    boolean changePassword(long userId, String password, String newPassword);
}
