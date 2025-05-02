package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class chefService {
    public static chef addTask(ArrayList<chef> chefs, String exp){
        ArrayList<chef> matchingChefs = new ArrayList<>();
       // chef best=null;
        for (chef c : chefs) {
            if (c.getExpertise().equals(exp)){
                matchingChefs.add(c);
            }
        }
        if (matchingChefs.isEmpty()) {
            return null;
        }
        int minTasks = matchingChefs.stream()
                .mapToInt(chef::getTaskCount)
                .min()
                .orElse(0);

        List<chef> final_chefs = matchingChefs.stream()
                .filter(c -> c.getTaskCount() == minTasks)
                .toList();
        return final_chefs.get(new Random().nextInt(final_chefs.size()));
       }
    }
