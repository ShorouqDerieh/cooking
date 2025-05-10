package org.example;

import java.util.ArrayList;

public class system {
    private ArrayList<ingredient> ingredients;
    public void check(){
        for(ingredient i : ingredients){
            if(i.getQuantity()<=i.getMinquantity()){
                System.out.println("need to restock"+i.getName()+"!");
            }
        }
    }
}
