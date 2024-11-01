package com.mscourse.limits_microservice.controller;

import com.mscourse.limits_microservice.bean.Limits;
import com.mscourse.limits_microservice.configuration.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private  Config config;
    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(config.getMinimum(),config.getMaximum());

    }
}
