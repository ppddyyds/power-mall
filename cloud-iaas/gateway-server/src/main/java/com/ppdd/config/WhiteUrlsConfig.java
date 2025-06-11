package com.ppdd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "gateway.white-list")
@Data
public class WhiteUrlsConfig {

    private List<String> urls;
}
