package com.microservices.api_gateway.configuration;


import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouteLocatorBuilder(RouteLocatorBuilder builder){
//        return  builder.routes().build(); //this is the default configuration

        Function<PredicateSpec, Buildable<Route>> routeFunction = predicateSpec ->
                predicateSpec.path("/get")
                        .filters(p-> p
                                .addRequestHeader("MyHeader","abcdefgh")
                                .addRequestParameter("MyParameter","abcdefgh")
                        )
                .uri("http://httpbin.org:80");
//        We can mark this as an inline function too , just body will be coming inside the folder

        return builder.routes()
                        .route(routeFunction)
                .route(p->p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(p->p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p->p.path("/currency-conversion-feign/**")
                        .filters(f->f.rewritePath("/currency-conversion-feign/(?<segment>.*)",
                                "/currency-conversion/${segment}"))
                        .uri("lb://currency-conversion"))
                .route(p->p.path("/currency-conversion-new/**")
                        .filters(f->f.rewritePath("/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
