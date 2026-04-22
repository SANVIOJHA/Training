package com.example.entity;

import jakarta.persistence.*;

//import jakarta.persistence;
@Entity
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String brandname;
    private double price;
    public Car(){

    }
    public Car(String brandName,double price){
        this.brandname=brandName;
        this.price=price;

    }

    public Long getId(){
        return id;
    }
    public String getBrandname(){
        return brandname;
    }
    public  void setBrandname(String brandname){
        this.brandname=brandname;
    }


    public void setPrice(double price){
        this.price=price;
    }
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brandname='" + brandname + '\'' +
                ", price=" + price +
                '}';
    }


}
