package com.bsm.fast.moudles.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsm.fast.moudles.sys.dao.SysUserDao;
import com.bsm.fast.moudles.sys.entity.SysUser;
import com.bsm.fast.moudles.sys.service.SysUserService;
import org.springframework.stereotype.Service;

@Service("SysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

}
