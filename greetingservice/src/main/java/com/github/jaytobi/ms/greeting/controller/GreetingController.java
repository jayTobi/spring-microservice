package com.github.jaytobi.ms.greeting.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/greeting/{name}")
public class GreetingController {
    private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);
    @Value("${greeting.friendly:Hello I'm the default greeting}")
    private String greeting;

    @GetMapping
    @RequestMapping(value = "/") //, method = RequestMethod.GET)
    public String greet(@PathVariable("name") String name) {
        String personalGreeting = greeting + " " + name;
        LOG.debug("personal greeting {}", personalGreeting);
        return personalGreeting;
    }
}
