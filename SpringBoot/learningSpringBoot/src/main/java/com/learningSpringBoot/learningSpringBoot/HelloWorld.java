package com.learningSpringBoot.learningSpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/")
    public String hello(){
        return "Hello World api heandling in Spring Boot";
    }
}
