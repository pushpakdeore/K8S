package com.example.user.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("test")
    public String great(){
        logger.warn("test Hello Controller ");
        return "Wellcome ";
    }
    @GetMapping("test1")
    public String great1(){
        logger.warn("[ALERT_403] Authentication failed for user: {} - Status: 403", "test2");
        return "Wellcome2 ";
    }
}
