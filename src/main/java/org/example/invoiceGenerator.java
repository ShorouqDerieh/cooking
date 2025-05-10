package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class invoiceGenerator {
    public void generateInvoice(String customerName, List<String> items, double totalPrice) {
        try {
            JasperReport report = JasperCompileManager.compileReport("src/main/resources/inv.jrxml");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("customerName", customerName);
            parameters.put("totalPrice", totalPrice);
            parameters.put("currentDate", new Date());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
                    items.stream().map(Item::new).toList()
            );
            //System.out.println(parameters);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(print, "Invoice_"+customerName+".pdf");
            System.out.println("Invoice generated successfully!");

        }
        catch (JRException e) {
            e.printStackTrace();
        }
    }
    public static class Item {
        private String item;

        public Item(String item) {
            this.item = item;
        }

        public String getItem() {
            return item;
        }
    }
}

