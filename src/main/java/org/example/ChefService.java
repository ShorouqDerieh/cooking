package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChefService {
    public static Chef addTask(ArrayList<Chef> Chefs, String exp){
        ArrayList<Chef> matchingChefs = new ArrayList<>();
       // chef best=null;
        for (Chef c : Chefs) {
            if (c.getExpertise().equals(exp)){
                matchingChefs.add(c);
            }
        }
        if (matchingChefs.isEmpty()) {
            return null;
        }
        int minTasks = matchingChefs.stream()
                .mapToInt(Chef::getTaskCount)
                .min()
                .orElse(0);

        List<Chef> final_chefs = matchingChefs.stream()
                .filter(c -> c.getTaskCount() == minTasks)
                .toList();
        return final_chefs.get(new Random().nextInt(final_chefs.size()));
       }
    }
