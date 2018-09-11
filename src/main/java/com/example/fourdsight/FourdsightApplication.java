package com.example.fourdsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication(scanBasePackages = "com.example.fourdsight")
public class FourdsightApplication {

	public static void main(String[] args) {
		SpringApplication.run(FourdsightApplication.class, args);
	}
}
