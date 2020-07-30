package com.bsm.fast.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bsm.fast.common.utils.PageUtils;
import com.bsm.fast.modules.sys.model.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser> {

    /**
     * 所有用户列表
     */
    PageUtils listSysUser(Map<String, Object> params);

    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    boolean changePassword(long userId, String password, String newPassword);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);
}
