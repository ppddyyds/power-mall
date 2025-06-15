package com.ppdd.factory;

import com.ppdd.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginStrategyFactory {
    @Autowired
    private Map<String, LoginStrategy> loginStrategyMap;

    public LoginStrategy getLoginStrategy(String loginType) {
        return loginStrategyMap.get(loginType);

    }
}
