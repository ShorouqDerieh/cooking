package org.example;

import java.util.List;

public class orders {
   public orderDisplay makeOrder(String name,List<supplier> supp,int quantity) {
      String suppliername = null;
      double price = 0;
      boolean found = false;
      for (supplier i : supp) {
         // price=i.getPrice(name);
         if (!found) {
            price = i.getPrice(name);
            suppliername = i.getName();
            found = true;
         }
         if (i.getPrice(name) < price && i.getPrice(name) != 0.0) {
            price = i.getPrice(name);
            suppliername = i.getName();
         }
      }
      if (suppliername != null) {
         return new orderDisplay(name, suppliername, price,quantity);
      }
      else
         return null;
   }
}
