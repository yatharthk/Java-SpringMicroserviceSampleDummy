package com.mscourse.limits_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimitsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitsMicroserviceApplication.class, args);


		System.out.println("********************** As u can see in above logs **************");
		System.out.println("Service is fetching configs and trying to start up using these below defined values");
		System.out.println("Limits ms is starting up..");
		System.out.println("Fetching config from server at : http://localhost:8888");
		System.out.println("Located environment: name=limits-service(basically the filename present in git repo), profiles=[default]");
		System.out.println("Final url will be http://localhost:8888/limits-service/default");
		System.out.println("If we are setting up new profiles. we can replace default with dev/qa based on env.");
		System.out.println("Good part is along with dev/qa/any other profiles - default profile configs will be returned");
		System.out.println("By default cloud [profiles=default] will be picked up If we need to get config from spring-cloud-config-server.");
		System.out.println("Use this line in app.properties. spring.cloud.config.profile=<profileToActivate>");
		System.out.println("In case we have configured a profile in config server different than the app name we using inside this service");
		System.out.println("We can use this line in app.properties.   spring.cloud.config.name=<name of properties file used for this service in cloud-config-server configurations>");
		System.out.println("Lets say we use config names as spring.cloud.config.name=limits-microservice then the url will be http://localhost:8888/limits-microservice/default");
	}

}
