package com.orders.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceHandler {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHandler.class, args);
	}

}

