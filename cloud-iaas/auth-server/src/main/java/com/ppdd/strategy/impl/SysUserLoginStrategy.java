package com.ppdd.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ppdd.constants.AuthConstants;
import com.ppdd.domain.LoginSysUser;
import com.ppdd.mapper.SysUserMapper;
import com.ppdd.model.SecurityUser;
import com.ppdd.strategy.LoginStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 商城管理系统用户登录策略
 */
@Service(AuthConstants.SYS_USER_LOGIN)
@RequiredArgsConstructor
public class SysUserLoginStrategy implements LoginStrategy {

    final SysUserMapper  sysUserMapper;

    @Override
    public UserDetails doLogin(String username) {
        LoginSysUser loginSysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<LoginSysUser>().eq(LoginSysUser::getUsername, username));

        if (loginSysUser != null) {
            //查询权限
            Set<String> userPermissions = sysUserMapper.getUserPermissions(loginSysUser.getUserId());
            //封装对象
            SecurityUser securityUser = new SecurityUser(
                    loginSysUser.getUserId(),
                    loginSysUser.getUsername(),
                    loginSysUser.getPassword(),
                    loginSysUser.getStatus(),
                    loginSysUser.getShopId(),
                    AuthConstants.SYS_USER_LOGIN,
                    userPermissions
            );
            return securityUser;

        }
        throw new UsernameNotFoundException("用户不存在");
    }

    @Override
    public String getLoginType() {
        return AuthConstants.SYS_USER_LOGIN;
    }
}
