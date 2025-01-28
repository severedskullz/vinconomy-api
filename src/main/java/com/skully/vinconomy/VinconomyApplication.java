package com.skully.vinconomy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.skully.*")
@EntityScan("com.skully")
public class VinconomyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinconomyApplication.class, args);
	}

}
