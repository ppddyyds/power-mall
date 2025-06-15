package com.ppdd.strategy.impl;

import com.ppdd.constants.AuthConstants;
import com.ppdd.strategy.LoginStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 商城会员登录
 */
@Service(AuthConstants.MEMBER_LOGIN)
public class MemberLoginStrategy implements LoginStrategy {



    @Override
    public UserDetails doLogin(String username) {
        return null;
    }

    @Override
    public String getLoginType() {
        return "";
    }
}
