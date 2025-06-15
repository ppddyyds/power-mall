package com.ppdd.config;

import com.ppdd.constants.AuthConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Feign拦截器：拦截Feign请求，实现Token传递
 * 浏览器->A->B
 */

@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String header = request.getHeader(AuthConstants.AUTH_HEADER);
            // 将token传递给下一个
            requestTemplate.header(AuthConstants.AUTH_HEADER, header);
        }

    }
}
