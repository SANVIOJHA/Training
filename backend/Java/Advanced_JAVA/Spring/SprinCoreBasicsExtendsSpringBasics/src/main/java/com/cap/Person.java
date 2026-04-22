package com.cap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * Person is also a Spring-managed bean.
 *
 * It depends on Mobile class.
 *
 * Instead of creating Mobile manually using:
 *    new Mobile();
 *
 * We let Spring inject it using Dependency Injection.
 */

@Component
public class Person {

    private final Mobile mobile;

    /*
     * Constructor Injection (Recommended way).
     *
     * Spring automatically detects this constructor
     * and injects Mobile bean into it.
     *
     * Since Mobile is singleton,
     * only one Mobile object will be injected.
     */

    @Autowired
    public Person(Mobile mobile) {
        this.mobile = mobile;
        System.out.println("Person object created with Mobile dependency");
    }
}