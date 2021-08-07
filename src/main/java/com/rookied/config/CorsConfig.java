package com.rookied.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author zhangqiang
 * @date 2021/8/6
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration config = new CorsConfiguration();
        //允许的协议+ip+端口 *表示所有
        //注意 localhost != 127.0.0.1
        config.addAllowedOrigin("*");
        //跨域请求头
        config.addAllowedHeader("*");
        //跨域请求方法 get put delete post
        config.addAllowedMethod("*");
        //这一句，表示可以带cookie，最终可以在跨域请求的时候获取同一个session
        //如果不加这一句，请求能进来，创建session，响应后会创建cookie，但是响应结果无法获取
        config.setAllowCredentials(true);
        return config;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //配置 可以访问的地址 必须是/** 不可以是/*
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
