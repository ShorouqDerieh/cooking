package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class priceFetch {
    public  Map<String, Double> getPricesForIngredient(String ingredientName, List<supplier> suppliers) {
        Map<String, Double> prices = new HashMap<>();
        for (supplier sup : suppliers) {
            Double price = sup.getPrice(ingredientName);
            if (price != null) {
                prices.put(sup.getName(), price);
            }
        }
        return prices;
    }
}
