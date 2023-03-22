package com.kgc.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class BackServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackServerApplication.class, args);
	}

}
