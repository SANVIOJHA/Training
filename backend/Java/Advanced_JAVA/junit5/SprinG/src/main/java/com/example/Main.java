package com.example;

public class Main {
    public static void main(String[] args) {
        Mobile m=new Mobile();

        m.setS(new Airtel());
        Sim data=m.getData(454);

    }
}
