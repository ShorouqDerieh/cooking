package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class expiryService {
    public List<String> generateExpiryAlerts(List<ingredient> ingredients, LocalDate today, int alertBeforeDays) {
        System.out.println("Checking expiry alerts on: " + today);
        for (ingredient item : ingredients) {
            System.out.println("Product: " + item.getName() + ", Expiry: " + item.getExpiryDate());
        }

        List<String> result = new ArrayList<>();
        StringBuilder message = new StringBuilder("The following products are approaching their expiry date:\n");
        boolean found = false;

        for (ingredient item : ingredients) {
            LocalDate expiry = item.getExpiryDate();
            if (expiry != null && !expiry.isBefore(today)) {
                long daysUntilExpiry = ChronoUnit.DAYS.between(today, expiry);
                if (daysUntilExpiry <= alertBeforeDays) {
                    message.append("- ").append(item.getName())
                            .append(" (ExpiryDate: ").append(expiry).append(")\n");
                    found = true;
                }
            }
        }

        if (found) {
            result.add("Subject: Near-Expiry Product Alert\n" + message.toString());
        }
        return result;
    }
    public void sendExpiryEmails(List<String> expiryEmails, kitchenManager manager) {
        EmailService emailService = new EmailService();
        for (String email : expiryEmails) {
            emailService.sendEmail(manager.getEmail(), "Near-Expiry Product Alert", email);
        }
    }

}
