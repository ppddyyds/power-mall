package com.ppdd.constants;

/**
 *  资源常量
 */
public class ResourceConstants {

    /**
     * 允许匿名访问的资源路径（白名单）
     */
    public static final String[] RESOURCE_ALLOWED_PATHS = {
            "/actuator/**",           // Spring Boot 监控端点（健康检查、metrics等）
            "/doc/**",                // Knife4j 或自定义文档接口前缀
            "/swagger-ui/**",         // Swagger3 UI 界面相关静态资源
            "/swagger-resources/**",  // Swagger 资源配置接口（用于UI分组、API元数据等）
            "/v3/api-docs/**",        // OpenAPI 3.0 接口文档数据（Springdoc-openapi）
            "/webjars/**",            // 静态第三方库，如js/css等（Spring Boot 自动打包）
            "/error/**",              // 全局错误页面（Spring Boot 默认错误处理）
            "/favicon.ico",           // 浏览器地址栏小图标
            "/**/*.css",              // 所有CSS静态资源
            "/**/*.js",               // 所有JS静态资源
            "/**/*.png",              // PNG图片静态资源
            "/**/*.jpg",              // JPG图片静态资源
            "/**/*.jpeg",             // JPEG图片静态资源
            "/**/*.gif",              // GIF图片静态资源
            "/**/*.svg"               // SVG矢量图片静态资源
    };
}
