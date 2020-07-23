package com.bsm.fast.modules.sys.OAuth2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bsm.fast.common.utils.ShiroUtils;
import com.bsm.fast.modules.sys.dao.SysUserDao;
import com.bsm.fast.modules.sys.model.entity.SysUser;
import com.bsm.fast.modules.sys.service.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * 认证
 *
 * @author: limingxin
 */
@Slf4j
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken accessToken = (UsernamePasswordToken)token;
        log.info("username:{},password:{}",accessToken.getUsername(),accessToken.getPassword());
        //根据UsernamePasswordToken，查询用户信息
        SysUser user = sysUserDao.selectOne(new QueryWrapper<SysUser>().eq("username",accessToken.getUsername()));
        log.info("user:{}",user);
        //判断账号不存在
        if (user == null ) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        log.info("getName():{}",getName());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        log.info(info.toString());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
