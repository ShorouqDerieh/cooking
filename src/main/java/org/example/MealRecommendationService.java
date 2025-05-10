package org.example;

import java.util.ArrayList;
import java.util.*;
public class MealRecommendationService {
    public List<String> recommendMeals(Customer customer) {
        // Example
        List<String> allMeals = Arrays.asList(
                "Vegetarian Salad",
                "Vegetarian Pasta",
                "Vegetarian Curry with peanuts",
                "Chicken Sandwich",
                "Beef Steak"
        );

        List<String> recommendations = new ArrayList<>();
        String pref = customer.getDietaryPreferences().toLowerCase();
        String allergy = customer.getAllergies().toLowerCase();

        for (String meal : allMeals) {
            if (meal.toLowerCase().contains(pref) && !meal.toLowerCase().contains(allergy)) {
                recommendations.add(meal);
            }
        }
        return recommendations;
    }
}
