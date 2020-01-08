package com.system.backgroundmanagement.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author luoyuequan
 * @time 2020/1/8 10:38
 */
@Configuration
@ConfigurationProperties(prefix = "news")
@PropertySource(value = {"classpath:config/file-upload.properties"}, encoding = "UTF-8")
@Data
public class NewsFileUploadConfig {
    @Value(value = "path")
    private String path;
    @Value(value = "size")
    private String size;
}
