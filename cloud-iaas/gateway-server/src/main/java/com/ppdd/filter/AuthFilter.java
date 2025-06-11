package com.ppdd.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppdd.config.WhiteUrlsConfig;
import com.ppdd.constants.AuthConstants;
import com.ppdd.constants.BusinessEnum;
import com.ppdd.constants.HttpConstants;
import com.ppdd.model.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {
    final StringRedisTemplate redisTemplate;
    final WhiteUrlsConfig whiteUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        //获取请求路径
        String path = request.getPath().toString();
        //判断请求路径
        if (whiteUrlsConfig.getUrls().contains(path)) {
            //包含在白名单中
            return chain.filter(exchange);
        }else {
            //不包含在白名单中,需要身份验证
            String authorizationValue = request.getHeaders().getFirst(AuthConstants.AUTH_HEADER);
            if (StringUtils.hasText(authorizationValue)) {
                //从请求头中获取token
                String token = authorizationValue.replace(AuthConstants.AUTH_TOKEN_TYPE, "");
                //判断token是否为空且是否在redis中存在
                if (StringUtils.hasText(token) && Boolean.TRUE.equals(redisTemplate.hasKey(AuthConstants.LOGIN_TOKEN_PREFIX + token))) {
                    //身份验证通过
                    return chain.filter(exchange);
                }
            }

        }

        //身份验证失败
        log.error("时间:{}，请求API:{}。拦截非法请求",  System.currentTimeMillis(), path);
        ServerHttpResponse response = exchange.getResponse();
        //设置响应头
        response.getHeaders().set(HttpConstants.CONTENT_Type,  HttpConstants.APPLICATION_JSON);
        //设置响应消息
        Result<Object> result = Result.error(BusinessEnum.UN_AUTHORIZED);
        //创建objectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
