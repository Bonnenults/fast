package com.bsm.fast.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bsm.fast.modules.sys.model.entity.SysUserRole;

import java.util.List;

/**
 * 用户角色关系
 *
 * @author: limingxin
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    List<Long> getRoleIdList(Long userId);

}
