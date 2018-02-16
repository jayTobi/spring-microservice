package com.github.jaytobi.ms.accounting.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/api/v1/accounting")
@RestController
public class SecureApiController {

    @PreAuthorize("hasRole('ADMIN')")  //here you can use SpringEL
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String greetingRoleBased() {
        return "Hello ADMIN...it's a nice day..." + LocalDateTime.now();
    }

    @PreAuthorize("hasPermission(#this, 'accounting.post')")
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String greetingPermissionBased() {
        return "hello, you called the POST method";
    }

    public String greetingAcl() {
        return "";
    }
}
