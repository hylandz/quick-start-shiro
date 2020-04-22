package com.xlx.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主程序启动类
 * tips:多模块需要添加扫描方式,否则只扫描当前模块
 */
@SpringBootApplication(scanBasePackages = "com.xlx")
@MapperScan("com.xlx.*.dao")
public class ShiroAdminApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ShiroAdminApplication.class, args);
    }
    
}
