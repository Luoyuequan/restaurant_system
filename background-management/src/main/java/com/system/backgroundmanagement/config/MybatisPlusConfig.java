package com.system.backgroundmanagement.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 分页插件
 * Spring boot方式
 * </p>
 *
 * @author luoyuequan
 * @date 2020/01/05
 * @time 下午 07:56
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}