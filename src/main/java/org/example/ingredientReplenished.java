package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ingredientReplenished {
    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Integer> minStock = new HashMap<>();
    private int orderQuantity;
    orderDisplay order;
    public  ingredientReplenished(Map<String, Integer> stock, Map<String, Integer> minStock, int orderQuantity) {
        this.stock = stock;
        this.minStock = minStock;
        this.orderQuantity = orderQuantity;
    }
    public void setStock(Map<String, Integer> stock) {
        this.stock = stock;
    }
    public Map<String, Integer> getStock() {
        return stock;
    }
    public void setMinStock(Map<String, Integer> minStock) {
        this.minStock = minStock;
    }
    public Map<String, Integer> getMinStock() {
        return minStock;
    }
    public orderDisplay check(String ingredient, List<supplier> suppliers) {
        int currentstock = stock.getOrDefault(ingredient, 0);
        int minimumstock = minStock.getOrDefault(ingredient, 0);
        boolean found = false;
        for (supplier s : suppliers) {
            if (s.getPrice(ingredient)!=0) {
                found = true;
                break;
            }
        }
        if (currentstock <= minimumstock&&found) {
            orders o = new orders();
            order = o.makeOrder(ingredient, suppliers, orderQuantity);

            if (order != null) {
                stock.put(ingredient, currentstock + orderQuantity);
                return order;
            }
        }
            System.out.println("No available supplier!");
            return null;
    }
}
