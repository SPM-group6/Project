package com.hwadee.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 跨域请求配置类
 */
@SpringBootConfiguration
public class CorsConfig implements WebMvcConfigurer {
    private static final String[] ORIGINS = new String[]{
            "GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(ORIGINS);
    }
}
