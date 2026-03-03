package com.example.springBasicsInitializer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hell {
    @GetMapping(path = "/hi")
    public String sayHell(){
        return  "done till here ";
    }
}
