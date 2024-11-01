package com.microservice.currency_conversion_service.controller;

import com.microservice.currency_conversion_service.Feign.CurrencyExchangeFeign;
import com.microservice.currency_conversion_service.model.CurrencyConversion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class CurrencyConversionController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeFeign currencyExchangeFeign;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable("from") String fromCurrency, @PathVariable("to") String toCurrency, @PathVariable("qty") BigDecimal quantity){
        CurrencyConversion conversionResponse = new CurrencyConversion();
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",fromCurrency);
        uriVariables.put("to",toCurrency);
//        conversionResponse.setId(1000L);
//        conversionResponse.setFrom(fromCurrency);
//        conversionResponse.setTo(toCurrency);
//        conversionResponse.setQuantity(quantity);
//        conversionResponse.setConversionMultiple(BigDecimal.valueOf(65.0));


        //calling another service from one using RestTemplate.getForEntity which accepts 3 params.
//        1.takes in uri, 2.class to be converted/mapped 3. variables/params in Hashmap
//        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
//        System.out.println(responseEntity.getBody());
        log.info("response entity is {}",responseEntity);

        CurrencyConversion currencyConversion = responseEntity.getBody();
        conversionResponse.setId(currencyConversion.getId());
        conversionResponse.setFrom(fromCurrency);
        conversionResponse.setTo(toCurrency);
        conversionResponse.setConversionMultiple(currencyConversion.getConversionMultiple());
        conversionResponse.setQuantity(quantity);
        conversionResponse.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));
        conversionResponse.setEnvironment(environment.getProperty("local.server.port") + "with rest template.");
        return conversionResponse;
    }

    @GetMapping("currency-conversion/feign/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable("from") String fromCurrency, @PathVariable("to") String toCurrency, @PathVariable("qty") BigDecimal quantity){
        CurrencyConversion conversionResponse = new CurrencyConversion();
        CurrencyConversion currencyConversion  = currencyExchangeFeign.retrieveExchangeValue(fromCurrency,toCurrency);
        conversionResponse.setId(currencyConversion.getId());
        conversionResponse.setFrom(fromCurrency);
        conversionResponse.setTo(toCurrency);
        conversionResponse.setConversionMultiple(currencyConversion.getConversionMultiple());
        conversionResponse.setQuantity(quantity);
        conversionResponse.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));
        System.out.println("Current service-instance is running on port"+ environment.getProperty("local.server.port"));
        conversionResponse.setEnvironment("Response returned with feign call to currencyExchange service from port: "+currencyConversion.getEnvironment());
        System.out.println("Feign call is load balanced automatically. due to fien client and load balancer auto imported with eureka client dependency");

        return conversionResponse;
    }
}
