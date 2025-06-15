package com.ppdd.constants;


public interface AuthConstants {

    String AUTH_HEADER = "Authorization";
    String AUTH_TOKEN_TYPE = "bearer";
    /**
     * token 值存放在redis中的前缀
     */
    String LOGIN_TOKEN_PREFIX = "login_token:";

    String LOGIN_URL  = "/doLogin";
    String LOGOUT_URL = "/doLogout";
    String LOGIN_TYPE = "loginType";
    String SYS_USER_LOGIN="sysUserLogin";
    String MEMBER_LOGIN="memberLogin";
    Long TokenExpire = 60 * 60 * 24L;
}
