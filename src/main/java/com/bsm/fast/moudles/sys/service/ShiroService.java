package com.bsm.fast.moudles.sys.service;

import com.bsm.fast.moudles.sys.model.entity.SysUser;
import com.bsm.fast.moudles.sys.model.entity.SysUserToken;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author limingxin
 */
public interface ShiroService {

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);


    SysUserToken getByToken(String token);

    /**
     * 根据userId 查询用户
     *
     * @param userId
     * @return
     */
    SysUser getUser(Long userId);
}
