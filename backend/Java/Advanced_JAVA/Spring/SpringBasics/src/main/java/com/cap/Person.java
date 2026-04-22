package com.cap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private Mobile mobile;
@Autowired
    public Person(Mobile mobile) {
        this.mobile = mobile;
        System.out.println("person created with mobile");
    }
}
