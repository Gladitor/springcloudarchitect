package com.jiay.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 服务网关启动类
 * @author jiay
 * @date 2020-03-17
 * @since 1.8
 */
@SpringCloudApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
