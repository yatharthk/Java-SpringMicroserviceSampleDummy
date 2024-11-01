package com.mscourse.limits_microservice.configuration;


import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//way1 to use @Value for reading out properties from app.properties
// way2 to use @ConfigurationProperties(value="limits-service")
// it will read the values starting with limits-service and after (.) it will read property name and map value to it.


@Component
@ConfigurationProperties(value = "limits-service")
@Data
public class Config {

//    @Value("${limits-service.minimum}") way1
    private int minimum;
//    @Value("${limits-service.maximum}")
    private int maximum;
}
