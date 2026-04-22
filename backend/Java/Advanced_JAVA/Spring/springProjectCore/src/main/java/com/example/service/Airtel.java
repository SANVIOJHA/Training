package com.example.service;

public class Airtel implements Sim {

    @Override
    public void calling() {
        System.out.println("Calling using Airtel SIM");
    }

    @Override
    public void data() {
        System.out.println("Browsing using Airtel data");
    }
}