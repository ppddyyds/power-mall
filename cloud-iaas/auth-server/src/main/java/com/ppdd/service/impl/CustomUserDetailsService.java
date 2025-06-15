package com.ppdd.service.impl;

import com.ppdd.constants.AuthConstants;
import com.ppdd.factory.LoginStrategyFactory;
import com.ppdd.strategy.LoginStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginStrategyFactory loginStrategyFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取请求上下文
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //从请求头中获取登录类型
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String loginType = request.getHeader(AuthConstants.LOGIN_TYPE);
            if (loginType == null) {
                throw new InternalAuthenticationServiceException("非法登录，登录类型出错");
            }
            LoginStrategy loginStrategy = loginStrategyFactory.getLoginStrategy(loginType);
            return loginStrategy.doLogin(username);
        }

        throw new InternalAuthenticationServiceException("未知错误");
    }
}
