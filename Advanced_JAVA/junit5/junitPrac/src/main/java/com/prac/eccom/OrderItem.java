package com.prac.eccom;


public class OrderItem {

    private double price;
    private int quantity;
    private String category;

    public OrderItem(double price, int quantity, String category) {
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

}
