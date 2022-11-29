package com.nothing.gateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Hodor_Zhu
 * @description
 * @date: 2022/11/24 18:22
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class StrayGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(StrayGatewayApplication.class, args);
    }
}
