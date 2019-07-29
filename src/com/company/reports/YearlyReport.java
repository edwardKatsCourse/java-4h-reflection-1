package com.company.reports;

public class YearlyReport implements Report {

    private String content;

    public YearlyReport(String content) {
        this.content = content;
    }

    @Override
    public String getFilename() {
        return "yearly.txt";
    }

    @Override
    public String fileContent() {
        return this.content;
    }
}
