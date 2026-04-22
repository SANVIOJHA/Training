package com.example;

public class Airtel implements Sim{

    @Override
    public boolean getData(int amount) {
        System.out.println("Airtel  "+amount);
        return true;
    }
}