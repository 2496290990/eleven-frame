package com.javacode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhaojinhui
 * @date 2021/1/19 21:25
 * @apiNote
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.javacode.mapper")
public class ServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class, args);
    }
}
