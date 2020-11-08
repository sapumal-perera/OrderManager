package com.orders.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItemManageModule {

	public static void main(String[] args) {
		SpringApplication.run(ItemManageModule.class, args);
	}

}

