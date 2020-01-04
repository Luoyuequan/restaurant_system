package com.system.backgroundmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台管理系统启动类
 *
 * @author 罗跃权
 */
@SpringBootApplication
@MapperScan("com.system.backgroundmanagement.mapper")
public class BackgroundManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackgroundManagementApplication.class, args);
    }

}
