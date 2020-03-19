package com.jiay.register;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心启动类
 * @author jiay
 * @date 2020-03-17
 * @since 1.8
 */
@SpringCloudApplication
@EnableEurekaServer
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
