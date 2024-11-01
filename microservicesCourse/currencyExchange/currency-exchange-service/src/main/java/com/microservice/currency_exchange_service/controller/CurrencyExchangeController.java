package com.microservice.currency_exchange_service.controller;

import com.microservice.currency_exchange_service.model.CurrencyExchange;
import com.microservice.currency_exchange_service.repository.CurrencyExchangeRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;


    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String fromCurrency,
                                                  @PathVariable("to") String toCurrency){
//        CurrencyExchange exchangedObject = new CurrencyExchange();
//        exchangedObject.setId(1000L);
//        exchangedObject.setFrom(fromCurrency);
//        exchangedObject.setTo(toCurrency);
//        exchangedObject.setConversionMultiple(BigDecimal.valueOf(65.00));
//        exchangedObject.setEnvironment(environment.getProperty("server.port"));
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(fromCurrency,toCurrency);
        if(currencyExchange == null){
            throw new RuntimeException("Unable to find data for "+fromCurrency+" and to "+toCurrency);
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
