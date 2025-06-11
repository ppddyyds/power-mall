package com.ppdd.constants;


public interface AuthConstants {

    String AUTH_HEADER = "Authorization";
    String AUTH_TOKEN_TYPE = "bearer";
    /**
     * token 值存放在redis中的前缀
     */
    String LOGIN_TOKEN_PREFIX = "login_token:";
}
