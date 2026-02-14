package com.lpu.service;

public class CalculationService {
    public static int add(int a,int b){
        return a+b;
    }
    public static int subs(int a,int b){
        return a-b;
    }
    public static int mul(int a,int b){
        return a*b;
    }
    public static int div(int a,int b){
        return a/b;
    }
    public static int anyNumOfNums(int... num) {
        int sum = 0;

        for (int n : num) {
            sum += n;
        }

        return sum;
    }
}
