package com.company.reports;

public class WeeklyReport implements Report {

    private String content;

    public WeeklyReport(String content) {
        this.content = content;
    }

    @Override
    public String getFilename() {
        return "weekly.txt";
    }

    @Override
    public String fileContent() {
        return this.content;
    }
}
