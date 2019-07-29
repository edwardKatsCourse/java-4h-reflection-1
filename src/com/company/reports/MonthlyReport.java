package com.company.reports;

public class MonthlyReport implements Report {

    private String content;

    public MonthlyReport(String content) {
        this.content = content;
    }

    @Override
    public String getFilename() {
        return "monthly.txt";
    }

    @Override
    public String fileContent() {
        return this.content;
    }
}
