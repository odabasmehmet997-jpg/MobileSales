package com.proje.mobilesales.core.reportparser;

import com.proje.mobilesales.features.reports.model.enums.ReportLayoutItemType;



public class ReportLayoutRow extends ReportLayoutItem {
    public ReportLayoutRow() {
        this.setItemType(ReportLayoutItemType.Row);
    }
}
