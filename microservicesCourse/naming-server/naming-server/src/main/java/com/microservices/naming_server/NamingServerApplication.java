package com.microservices.naming_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NamingServerApplication {

	public static void main(String[] args) {

		System.out.println("Starting up eureka server at localhost:8761");
		SpringApplication.run(NamingServerApplication.class, args);
		System.out.println("To register new services to use this , add eureka-client-dependency in pom of respective services");
	}

}
