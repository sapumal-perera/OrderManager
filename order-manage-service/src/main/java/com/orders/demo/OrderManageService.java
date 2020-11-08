package com.orders.demo;

import com.orders.demo.models.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class OrderManageService {

	public static void main(String[] args) {
		SpringApplication.run(OrderManageService.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/{client}")
	public Order getClientByID(@PathVariable("client") String clientId) {
		return new Order();
	}

	@RequestMapping("/all")
	public List<Order> getAllClients() {
		return null;
	}
}

