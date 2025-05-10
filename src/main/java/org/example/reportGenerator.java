package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class reportGenerator {
    private String selectedMonth;
    private String reportDate;
    private List<Data> salesDataList;
    public  reportGenerator(){
        salesDataList=new ArrayList<>();
    }
    public void setSelectedMonth(String selectedMonth) {
        this.selectedMonth = selectedMonth;
    }
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    public void setSalesDataList(List<Data> salesDataList) {
        this.salesDataList = salesDataList;
    }
    public void addSalesData(String name, int quantity, double total, LocalDate date) {
        if ((date.getMonth().name() + " " + date.getYear()).equalsIgnoreCase(selectedMonth)) {
            this.salesDataList.add(new Data(name, quantity, total,date));
        }
    }
    public String getReportDate(){
        return reportDate;
    }
    public void generateReport() throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/main/resources/report.jrxml");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("selectedMonth", this.selectedMonth);
        parameters.put("reportDate", this.reportDate);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(this.salesDataList);
        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(print, "Report_.pdf");
        System.out.println("Invoice generated successfully!");
    }
    public boolean hasData(){
        return !salesDataList.isEmpty();
    }
}
