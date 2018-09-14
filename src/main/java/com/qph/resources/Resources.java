package com.qph.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Resources {

    @RequestMapping(value = "/user")
    public Principal getUser(Principal user) {
        return user;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello world!";
    }
}
