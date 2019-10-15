package com.risesin.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigurationProperties
@EnableEurekaClient
public class RisesinPaymentserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RisesinPaymentserviceApplication.class, args);
    }

}
