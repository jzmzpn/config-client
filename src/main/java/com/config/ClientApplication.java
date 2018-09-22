package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ClientApplication.class, args);
		Object password = run.getEnvironment().getProperty("password");
		System.out.println(password);
	}
}
