package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class lowStockService {
    EmailService service = new EmailService();
     List<String> emails ;
    kitchenManager manager;
    boolean send = false;

    public lowStockService(List<String> emails, kitchenManager manager) {
        this.emails = emails;
        this.manager = manager;
    }
    public lowStockService(){

    }

    public boolean check() {
        for (String email : emails) {
            service.sendEmail(manager.getEmail(), "Low stock Alert", email);
            send = true;
        }
        return send;
    }

    public  List<String> generateEmails(List<ingredient> ingredients, Map<String, Boolean> sending

    ) {
       List<String> emails = new ArrayList<>();
        for (ingredient item : ingredients) {
            if (item.getQuantity() <= item.getMinquantity() && !sending.get(item.getName())) {
                String email = "Low Stock of " + item.getName() +
                        " Current Stock: " + item.getQuantity() +
                        " Minimum Stock: " + item.getMinquantity();
                emails.add(email);
                sending.put(item.getName(),true);
            }
        }
        return emails;
    }
}