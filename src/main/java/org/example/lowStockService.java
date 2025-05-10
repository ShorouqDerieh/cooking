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
    public List<String> generateDailySummaryEmail(List<ingredient> ingredients) {
        List<String> result = new ArrayList<>();
        StringBuilder message = new StringBuilder("The following ingredients are below their minimum stock levels:\n");
        boolean found = false;

        for (ingredient item : ingredients) {
            if (item.getQuantity() < item.getMinquantity()) {
                message.append("- ").append(item.getName())
                        .append(" (Stock: ").append(item.getQuantity())
                        .append(", Min: ").append(item.getMinquantity()).append(")\n");
                found = true;
            }
        }

        if (found) {
            result.add("Subject: Daily Low-Stock Summary\n" + message.toString());
        }
        return result;
    }

    public void sendDailySummary(String subject) {
        for (String email : emails) {
            service.sendEmail(manager.getEmail(), subject, email);
            send = true;
        }
    }
}