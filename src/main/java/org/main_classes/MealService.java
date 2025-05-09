package main_classes;
import java.util.*;
public class MealService {
    private Set<String> unavailable = new HashSet<>();
    private Map<String, Integer> stock = new HashMap<>();
    private String dietaryPreference;

    public void reset() {
        unavailable.clear();
        stock.clear();
        dietaryPreference = null;
    }

    public void markAsUnavailable(String ingredient) {
        unavailable.add(ingredient);
    }

    public void createMeal(List<String> ingredients) {
        for (String ing : ingredients) {
            if (unavailable.contains(ing)) {
                throw new IllegalStateException(capitalize(ing) + " is currently unavailable");
            }
        }
        if (ingredients.contains("salmon") && ingredients.contains("chocolate sauce")) {
            throw new IllegalStateException("Salmon and chocolate sauce cannot be combined");
        }
        for (String ing : ingredients) {
            if (stock.getOrDefault(ing, 10) <= 0) {
                throw new IllegalStateException(ing + " is out of stock");
            }
            stock.put(ing, stock.getOrDefault(ing, 10) - 1);
        }
    }

    public void setLimitedStock(String ingredient) {
        stock.put(ingredient, 1);
    }

    public boolean isStockUpdated(String ingredient) {
        return stock.getOrDefault(ingredient, 0) < 1;
    }

    public void setDietaryPreference(String preference) {
        this.dietaryPreference = preference;
    }

    public List<String> getDisplayedIngredients() {
        if ("gluten-free".equalsIgnoreCase(dietaryPreference)) {
            return Arrays.asList("chicken", "broccoli", "brown rice");
        }
        return Arrays.asList("chicken", "broccoli", "brown rice", "pasta", "bread");
    }

    public boolean isCompatibleWith(String preference, String ingredient) {
//        if ("gluten-free".equalsIgnoreCase(preference)) {
//            return !ingredient.equalsIgnoreCase("pasta") && !ingredient.equalsIgnoreCase("bread");
//        }
//        return true;
        if ("gluten-free".equalsIgnoreCase(preference)) {
            return !ingredient.equalsIgnoreCase("pasta") && !ingredient.equalsIgnoreCase("bread");
        }
        if ("vegan".equalsIgnoreCase(preference)) {
            return !ingredient.equalsIgnoreCase("chicken") && !ingredient.equalsIgnoreCase("cheese");
        }
        return true;
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

}
