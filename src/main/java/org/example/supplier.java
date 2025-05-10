package org.example;

import java.util.HashMap;
import java.util.Map;

public class supplier {
    String name;
    private Map<String, Double> prices;
    public supplier(String name) {
        this.name = name;
        this.prices = new HashMap<>();
    }
    public String getName() {
        return name;
    }
    public void addPrice(String i,double price) {
        this.prices.put(i, price);
    }
    public double getPrice(String i) {
        if(this.prices.get(i) == null) {
            return 0.0;
        }
        return this.prices.get(i);
    }

}
