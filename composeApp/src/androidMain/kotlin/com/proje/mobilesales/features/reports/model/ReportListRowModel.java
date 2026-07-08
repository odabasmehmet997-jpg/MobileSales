package com.proje.mobilesales.features.reports.model;

import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.features.userreport.model.database.UserReports;

public final class ReportListRowModel {
    private Class<?> clazz;

    public int f1271id;
    public boolean isUserDefined;
    private int reportType;
    private ProcessType type;
    public UserReports userReport;

    public int getReportType() {
        return reportType;
    }

    public void setReportType(final int i2) {
        reportType = i2;
    }

    public ProcessType getType() {
        return type;
    }

    public void setType(final ProcessType processType) {
        type = processType;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(final Class<?> cls) {
        clazz = cls;
    }

    public ReportListRowModel(final int i2) {
        f1271id = i2;
    }

    public ReportListRowModel(final int i2, final UserReports userReports) {
        f1271id = i2;
        userReport = userReports;
    }
}
