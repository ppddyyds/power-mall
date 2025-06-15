package com.ppdd.strategy;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录策略
 */
public interface LoginStrategy {
    /**
     * 执行登录操作
     *
     * @param username 登录请求参数
     * @return
     */
    UserDetails doLogin(String username);
    /**
     * 支持的登录类型，例如 "password"、"sms"、"wechat"等
     */
    String getLoginType();
}
