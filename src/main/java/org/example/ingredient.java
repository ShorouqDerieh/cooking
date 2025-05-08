package org.example;

public class ingredient {
    private String name;
    private int quantity;
    private int minquantity;
    private double price;
    public ingredient() {

    }
    public ingredient(String name, int quantity, int minquantity) {
        this.name = name;
        this.quantity = quantity;
        this.minquantity = minquantity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getMinquantity() {
        return minquantity;
    }
    public void setMinquantity(int minquantity) {
        this.minquantity = minquantity;
    }
    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", minquantity=" + minquantity +
                '}';
    }


}