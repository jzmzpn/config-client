package com.config.expansions;

import com.config.expansions.annotation.EnableCustomConfigServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCustomConfigServer
//@EnableConfigServer
public class ConfigStudyApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ConfigStudyApplication.class, args);
	}
}
