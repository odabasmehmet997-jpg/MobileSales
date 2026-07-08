package com.proje.mobilesales.core.reportparser;


/* compiled from: ReportDesignHandler.java */

class ReportPropertySearchResult {
    public ReportProperty foundProperty;
    public ReportProperty parentProperty;

    ReportPropertySearchResult(final ReportProperty reportProperty, final ReportProperty reportProperty2) {
        foundProperty = reportProperty;
        parentProperty = reportProperty2;
    }
}
