package com.system.backgroundmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author luoyuequan
 * @time 2020/1/9 15:03
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    InterceptorHandlerConfig interceptorHandlerConfig;

    /**
     * 拦截器注册添加
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(interceptorHandlerConfig);
        registration.excludePathPatterns("/static/**").addPathPatterns("/**");
    }

    /**
     * 静态资源文件处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //可以通过http://127.0.0.1:8080/web/home.html访问resources/web/home.html页面
        registry.addResourceHandler("/static/**", "/webjars/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 全局跨域访问配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedOrigins("*");
    }
}
