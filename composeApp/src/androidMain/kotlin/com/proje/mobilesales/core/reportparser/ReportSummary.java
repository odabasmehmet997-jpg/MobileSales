package com.proje.mobilesales.core.reportparser;



public class ReportSummary {
    private String calculatedValue;
    private String columnName;
    private String text;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(final String str) {
        type = str;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(final String str) {
        columnName = str;
    }

    public String getText() {
        return text;
    }

    public void setText(final String str) {
        text = str;
    }

    public String getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(final String str) {
        calculatedValue = str;
    }
}
