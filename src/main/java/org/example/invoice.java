package org.example;

import java.util.List;

public class invoice {
    String name;
    List<String> items;
    double price;
    public invoice(String name, List<String> items, double price) {
        this.name = name;
        this.items = items;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getItems() {
        return items;
    }
    public void setItems(List<String> items) {
        this.items = items;

    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
