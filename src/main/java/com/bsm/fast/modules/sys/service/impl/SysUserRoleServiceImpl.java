package com.bsm.fast.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsm.fast.modules.sys.dao.SysUserRoleDao;
import com.bsm.fast.modules.sys.model.entity.SysUserRole;
import com.bsm.fast.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色关系
 *
 * @author: limingxin
 * @time: 2020/7/21 0021 10:11
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Override
    public List<Long> getRoleIdList(Long userId) {
        return this.getRoleIdList(userId);
    }
}
