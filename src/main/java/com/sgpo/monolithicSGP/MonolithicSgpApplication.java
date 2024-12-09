package com.sgpo.monolithicSGP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MonolithicSgpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolithicSgpApplication.class, args);
	}

}
