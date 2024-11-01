package com.microservice.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("sample-api")
//    @Retry(name = "sample-api-endpoint",fallbackMethod = "sampleFallBackMethod")
//    @CircuitBreaker(name = "sample-api-endpoint",fallbackMethod = "sampleFallBackMethod")
    @Bulkhead(name="sample-api")
    public String anySampleApi(){
        log.info("Inside sampleAPi");
        log.info("while defining retryer in case of fallback method definition Exception parameter is must");
        return new RestTemplate().getForObject("http://localchost:9090",String.class);
//        return "dummyResponseSuccess";
    }

    @GetMapping("/sample-api2")
    @RateLimiter(name = "default")
    public String sampleFallBackMethod(Exception e){
        log.info("inside fallback method");
        return "Fallback method response";
    }
}
