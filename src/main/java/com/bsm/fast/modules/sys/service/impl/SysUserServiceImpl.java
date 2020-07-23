package com.bsm.fast.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsm.fast.common.domain.Query;
import com.bsm.fast.common.utils.PageUtils;
import com.bsm.fast.modules.sys.dao.SysUserDao;
import com.bsm.fast.modules.sys.model.entity.SysUser;
import com.bsm.fast.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("SysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Override
    public PageUtils listSysUser(Map<String,Object> params){
        String username = (String)params.get("username");
        Long createUserId = (Long)params.get("createUserId");

        IPage<SysUser> page = this.page(
                new Query<SysUser>().getPage(params),
                new QueryWrapper<SysUser>()
                        .like(StringUtils.isNotBlank(username),"username",username)
                        .eq(createUserId != null,"create_user_id",createUserId)
        );
        return new PageUtils(page);
    }
}
