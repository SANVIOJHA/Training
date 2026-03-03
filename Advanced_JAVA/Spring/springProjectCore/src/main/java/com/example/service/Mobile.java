package com.example.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mobile {

    private Sim sim;

    // Constructor Injection
    public Mobile(Sim sim) {
        this.sim = sim;
    }

    public void usePhone() {
        sim.calling();
        sim.data();
    }

    //  Main method
    public static  void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Mobile mobile = context.getBean("mobile", Mobile.class);

        mobile.usePhone();
    }
}