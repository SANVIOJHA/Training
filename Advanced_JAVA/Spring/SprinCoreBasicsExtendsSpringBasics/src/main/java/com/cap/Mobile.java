package com.cap;

import org.springframework.stereotype.Component;

/*
 * @Component registers this class as a Spring bean.
 *
 * Since no scope is defined,
 * default scope = singleton.
 *
 * That means only ONE Mobile object
 * will be created when container starts.
 */

@Component
public class Mobile {

    public Mobile() {
        System.out.println("Mobile object created");
    }
}