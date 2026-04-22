package com.example.service;

public class Vi implements Sim {

    @Override
    public void calling() {
        System.out.println("Calling using VI SIM");
    }

    @Override
    public void data() {
        System.out.println("Browsing using VI data");
    }
}