package org.example;

import java.time.LocalDate;

public class Data {
    private String productName;
    private int quantitySold;
    private double totalRevenue;
    private LocalDate date;
    public Data(String productName, int quantitySold, double totalRevenue,LocalDate date) {
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalRevenue = totalRevenue;
        this.date = date;
    }
    public String getProductName() {
        return productName;
    }
    public int getQuantitySold() {
        return quantitySold;
    }
    public double getTotalRevenue() {
        return totalRevenue;
    }
    public LocalDate getDate() {
        return date;
    }

}
