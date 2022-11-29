package com.nothing.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Hodor_Zhu
 * @description
 * @date: 2022/11/25 23:06
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class StrayUserService {
    public static void main(String[] args) {
        SpringApplication.run(StrayUserService.class, args);
    }
}
