package com.proje.mobilesales.core.reportparser;



public class ReportLayoutColumn extends ReportLayoutItem {
    private String columnName;
    private ReportColumn reportColumn;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(final String str) {
        columnName = str;
    }

    public ReportColumn getReportColumn() {
        return reportColumn;
    }

    public void setReportColumn(final ReportColumn reportColumn) {
        this.reportColumn = reportColumn;
    }
}
