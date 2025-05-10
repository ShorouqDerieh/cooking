package org.example;

public class orderDisplay {
    private String name;
    private String supplier;
    private double price;
    private int quantity;
    public orderDisplay(String name,String supplier,double price,int quantity){
        this.name = name;
        this.supplier = supplier;
        this.price = price;
        this.quantity = quantity;
    }
    public void PrintOrder(){
        System.out.println("Ordered successfully:");
        System.out.println("Quantity "+quantity);
        System.out.println("Ingredient: "+this.name);
        System.out.println("From: "+this.supplier);
        System.out.println("Price: "+this.price);
    }
}