package com.ppdd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "gateway")
@Data
public class WhiteUrlsConfig {

    private List<String> whiteList;
}
