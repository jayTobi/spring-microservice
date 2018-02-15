package com.github.jaytobi.ms.customer.controller;

import com.github.jaytobi.ms.customer.domain.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 */
@RestController
public class CustomerController {
    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/customer")
    @GetMapping
    public HttpEntity<Customer> greeting() {
        Customer greeting = new Customer();
        greeting.add(linkTo(methodOn(CustomerController.class).greeting()).withSelfRel());
        return new ResponseEntity(greeting, HttpStatus.OK);
    }
}
