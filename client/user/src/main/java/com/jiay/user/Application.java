package com.jiay.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户服务启动类
 * @author jiay
 * @date 2020-03-17
 * @since 1.8
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.jiay")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
