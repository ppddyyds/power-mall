package com.ppdd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppdd.constants.BusinessEnum;
import com.ppdd.constants.HttpConstants;
import com.ppdd.constants.ResourceConstants;
import com.ppdd.filter.TokenTranslationFilter;
import com.ppdd.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * 资源服务器配置
 *
 * @author ppdd
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig {

    @Autowired
    private TokenTranslationFilter tokenTranslationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                // token解析器
                .addFilterBefore(tokenTranslationFilter, UsernamePasswordAuthenticationFilter.class)

                // 异常处理
                .exceptionHandling(exception -> exception
                        // 权限不足处理
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setContentType(HttpConstants.APPLICATION_JSON);
                            response.setCharacterEncoding(HttpConstants.UTF_8);
                            Result<Object> error = Result.error(BusinessEnum.UN_AUTHORIZED);
                            String s = objectMapper.writeValueAsString(error);
                            PrintWriter writer = response.getWriter();
                            writer.write(s);
                            writer.flush();
                            writer.close();
                        })
                        // 认证失败处理
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType(HttpConstants.APPLICATION_JSON);
                            response.setCharacterEncoding(HttpConstants.UTF_8);
                            Result<Object> error = Result.error(BusinessEnum.OPERATION_FAIL);
                            String s = objectMapper.writeValueAsString(error);
                            PrintWriter writer = response.getWriter();
                            writer.write(s);
                            writer.flush();
                            writer.close();
                        })
                );
        // 授权
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(ResourceConstants.RESOURCE_ALLOWED_PATHS).permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }

}
