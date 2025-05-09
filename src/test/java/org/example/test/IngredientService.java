package org.example.test;
import java.util.*;
public class IngredientService {
    private Map<String, String> substitutes = new HashMap<>();
    private Set<String> unavailableIngredients = new HashSet<>();
    private boolean chefAlerted;

    public IngredientService() {
        substitutes.put("lobster", "shrimp");
        substitutes.put("chicken", "tofu");
        unavailableIngredients.add("truffle");
    }

    public String suggestSubstitute(String ingredient) {
        String substitute = substitutes.get(ingredient.toLowerCase());
        if (substitute != null) {
            return substitute;
        } else {
            return "Incorrect substitute suggested";
        }
    }

    public String getErrorMessage(String ingredient) {
        if (unavailableIngredients.contains(ingredient.toLowerCase())) {
            if (!substitutes.containsKey(ingredient.toLowerCase())) {
                return capitalize(ingredient) + " is currently unavailable and no substitute is available";
            }
            return capitalize(ingredient) + " is currently unavailable";
        }
        return "";
    }

    public void submitCustomMeal(List<String> ingredients) {
        chefAlerted = false;
        for (String ingredient : ingredients) {
            if (unavailableIngredients.contains(ingredient.toLowerCase()) || substitutes.containsKey(ingredient.toLowerCase())) {
                chefAlerted = true;
                break;
            }
        }
    }

    public boolean isChefAlerted() {
        return chefAlerted;
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
