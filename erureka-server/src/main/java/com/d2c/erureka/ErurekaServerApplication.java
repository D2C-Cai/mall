package com.d2c.erureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ErurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErurekaServerApplication.class, args);
    }

}

