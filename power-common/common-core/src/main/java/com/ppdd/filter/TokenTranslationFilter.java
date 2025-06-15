package com.ppdd.filter;

import com.alibaba.nacos.common.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppdd.constants.AuthConstants;
import com.ppdd.model.UserModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 令牌转换过滤器（TokenTranslationFilter）
 *
 * 【问题说明】：
 * 1. 在微服务架构下，认证中心（Auth服务）颁发的token实际保存的是一个通用的UserModel（DTO）对象，并非Spring Security的UserDetails实现。
 * 2. Resource服务收到请求时，需要从Redis读取用户信息（UserModel），但Spring Security上下文要求设置Authentication对象，不能直接放UserModel，否则无法进行权限判断和方法级@PreAuthorize鉴权。
 * 3. 为此，本Filter在每次请求时：
 *    - 从请求头解析token，从Redis中获取并反序列化用户信息（UserModel）；
 *    - 将用户权限（已扁平化的权限字符串）转为Spring Security认可的GrantedAuthority集合；
 *    - 构造标准的UsernamePasswordAuthenticationToken对象，设置到SecurityContextHolder上下文中；
 *    - 这样即可兼容Spring Security的鉴权体系，支持注解和全局拦截。
 *
 * 【注意】：
 * - 若没有token或token无效，不设置认证上下文，由Spring Security继续返回未认证状态。
 * - 本类依赖于Spring Security的核心类（如GrantedAuthority），common-model模块仅需定义UserModel即可，安全实现细节由各服务自行处理解耦。
 */

@Component
public class TokenTranslationFilter extends OncePerRequestFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AuthConstants.AUTH_HEADER);
        if (authorization != null) {
            String token = authorization.replace(AuthConstants.AUTH_TOKEN_TYPE,  "");
            //判断token是否为空
            if (StringUtils.hasText(token)) {
                //从redis中获取token对应的用户信息
                String s = stringRedisTemplate.opsForValue().get(AuthConstants.LOGIN_TOKEN_PREFIX + token);
                //判断token是否为空
                if (StringUtils.hasText(s)) {
                    //token续签
                    Long expire = stringRedisTemplate.getExpire(AuthConstants.LOGIN_TOKEN_PREFIX + token);
                    //判断token是否超过阈值
                    if (expire < AuthConstants.TOKEN_THRESHOLD){
                        stringRedisTemplate.expire(AuthConstants.LOGIN_TOKEN_PREFIX + token, Duration.ofSeconds(AuthConstants.TokenExpire));
                    }

                    //转换成对象
                    UserModel securityUser = new ObjectMapper().readValue(s, UserModel.class);
                    Set<GrantedAuthority> authorities = securityUser.getPermissions()
                            .stream()
                            .filter(StringUtils::hasText)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toSet());

                    //将认证对象放到容器上下文
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(securityUser, null, authorities)
                    );
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
