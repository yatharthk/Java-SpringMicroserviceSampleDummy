package com.microservice.currency_conversion_service.Feign;

import com.microservice.currency_conversion_service.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//this "feign" client will connect based on url. base url should be mentioned inside this url property,
// otherwise it will end up in 404 not found.
//@EnableFeignClient annotation needs to be added in the main class.
// we can now use this interface, autowire in controller and use as normal dependency.

//Methods defined here doesn't need a body (since it is an interface). it will fetch the values and
// replace in the endpoint and hit it.

//Remove this url from annotation and service url will get picked up from eureka service
// and automatic load balance will happen
//@FeignClient(name = "currency-exchange", url = "http://localhost:8000/currency-exchange")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeFeign {

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable("from") String fromCurrency,
                                             @PathVariable("to") String toCurrency);

}
