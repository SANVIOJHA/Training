package com.example;

public class Mobile {

    Sim s;
    public Sim getData(int amount) {
        System.out.println(amount);
        return s;
    }

    public void setS(Sim s) {
        this.s = s;
    }

    //    public static void main(String[] args){
//    Sim a=new Airtel();
//    a.getData(1000) ;
//    Sim v=new Vi();
//    v.getData(1221);
//}

}
