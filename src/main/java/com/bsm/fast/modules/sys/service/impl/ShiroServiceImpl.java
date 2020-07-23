package com.bsm.fast.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bsm.fast.common.constant.Constants;
import com.bsm.fast.modules.sys.dao.SysMenuDao;
import com.bsm.fast.modules.sys.dao.SysUserDao;
import com.bsm.fast.modules.sys.dao.SysUserTokenDao;
import com.bsm.fast.modules.sys.model.entity.SysMenu;
import com.bsm.fast.modules.sys.model.entity.SysUser;
import com.bsm.fast.modules.sys.model.entity.SysUserToken;
import com.bsm.fast.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description:
 * @author: limingxin
 * @time: 2020/7/21 0021 10:11
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
        if (userId == Constants.SUPER_ADMIN) {
            List<SysMenu> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenu menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserToken getByToken(String token){
        return sysUserTokenDao.selectOne(new QueryWrapper<SysUserToken>().eq("token",token));
    }

    @Override
    public SysUser getUser(Long userId) {
        return sysUserDao.selectById(userId);
    }

}
