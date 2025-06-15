package com.ppdd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppdd.constants.AuthConstants;
import com.ppdd.constants.BusinessEnum;
import com.ppdd.constants.HttpConstants;
import com.ppdd.domain.LoginResult;
import com.ppdd.model.Result;
import com.ppdd.model.SecurityUser;
import com.ppdd.model.UserModel;
import com.ppdd.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfig  {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private CustomUserDetailsService CustomUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF 跨域
                .cors(AbstractHttpConfigurer::disable) // 禁用 CORS 跨站
                .formLogin(AbstractHttpConfigurer::disable) // 禁用默认登录表单
                .httpBasic(AbstractHttpConfigurer::disable) // 禁用 Basic Auth
                .sessionManagement(AbstractHttpConfigurer::disable) // 禁用 Session
                .userDetailsService(CustomUserDetailsService)
                //配置登录信息
                .formLogin(form ->form
                        .loginProcessingUrl(AuthConstants.LOGIN_URL) //  登录url
                        .successHandler(authenticationSuccessHandler()) //登录成功处理器
                        .failureHandler(authenticationFailureHandler()) //登录失败处理器
                )
                .logout(logout -> logout
                        .logoutUrl(AuthConstants.LOGOUT_URL) // 登出url
                        .logoutSuccessHandler(logoutSuccessHandler()) // 登出成功处理器
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AuthConstants.LOGIN_URL, AuthConstants.LOGOUT_URL).permitAll() // 放行登录/注册接口
                        .anyRequest().authenticated() // 其余接口需认证（通常交给网关判断）
                );
        return http.build();
    }

    /**
     * 登录成功处理器
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            //设置响应头
            response.setContentType(HttpConstants.APPLICATION_JSON);
            response.setCharacterEncoding(HttpConstants.UTF_8);
            //使用uuid当token
            String token = UUID.randomUUID().toString();
            // 从SecurityUser中提取业务信息，构建UserModel
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            UserModel userDTO = new UserModel(
                    securityUser.getUserId(),
                    securityUser.getUsername(),
                    securityUser.getStatus(),
                    securityUser.getShopId(),
                    securityUser.getLoginType(),
                    securityUser.getPermissions()
            );
            //转换成json
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(userDTO);

            //将token当成key,json当成value保存到redis中
            redisTemplate.opsForValue()
                    .set(AuthConstants.LOGIN_TOKEN_PREFIX+token
                            , json
                            , Duration.ofSeconds(AuthConstants.TokenExpire));
            //返回token给前端
            LoginResult loginResult = new LoginResult(token, AuthConstants.TokenExpire);
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(loginResult));
            writer.flush();
            writer.close();

        };
    }

    /**
     * 登录失败处理器
     * @return
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            //设置响应头
            response.setContentType(HttpConstants.APPLICATION_JSON);
            response.setCharacterEncoding(HttpConstants.UTF_8);
            //返回错误信息给前端
            Result<Object> result = Result.error(BusinessEnum.OPERATION_FAIL.getCode(),exception.getMessage(),null);
            PrintWriter writer = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(result));
            writer.flush();
            writer.close();
        };
    }

    /**
     * 登出成功处理器
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            //设置响应头
            response.setContentType(HttpConstants.APPLICATION_JSON);
            response.setCharacterEncoding(HttpConstants.UTF_8);
            //从请求头获取token删除redis
            String token = request.getHeader(AuthConstants.AUTH_HEADER).replace(AuthConstants.AUTH_TOKEN_TYPE, "");
            redisTemplate.delete(AuthConstants.LOGIN_TOKEN_PREFIX+token);
            //返回成功信息给前端
            PrintWriter writer = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(Result.success()));
            writer.flush();
            writer.close();
        };
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
