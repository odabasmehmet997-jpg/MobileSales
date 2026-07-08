package com.proje.mobilesales.core.reportparser;

import com.proje.mobilesales.features.reports.model.enums.ReportDisplayType;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;



public class Report {
    private Hashtable<String, String> cloneReportDetails;
    private String columnWidths;
    private String defaultLanguage;
    private ReportDisplayType displayType;
    private ReportLayout mReportLayout;
    private List<ReportParam> mReportParams;
    private List<ReportTranslation> mReportTranslations;
    private String orgSql;
    private String remoteReport;
    private Hashtable<String, String> reportDetails;
    private String sql;
    private List<ReportSummary> summaries;
    private String wcfOrderBy;

    public String getSql() {
        return sql;
    }

    public void setSql(final String str) {
        sql = str;
    }

    public String getColumnWidths() {
        return columnWidths;
    }

    public void setColumnWidths(final String str) {
        columnWidths = str;
    }

    public String getRemoteReport() {
        return remoteReport;
    }

    public void setRemoteReport(final String str) {
        remoteReport = str;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(final String str) {
        defaultLanguage = str;
    }

    public List<ReportTranslation> getReportTranslations() {
        return mReportTranslations;
    }

    public void setReportTranslations(final List<ReportTranslation> list) {
        mReportTranslations = list;
    }

    public void addReportTranslation(final ReportTranslation reportTranslation) {
        if (null == this.mReportTranslations) {
            mReportTranslations = new ArrayList();
        }
        mReportTranslations.add(reportTranslation);
    }

    public List<ReportParam> getReportParams() {
        return mReportParams;
    }

    public void setReportParams(final List<ReportParam> list) {
        mReportParams = list;
    }

    public void addReportParam(final ReportParam reportParam) {
        if (null == this.mReportParams) {
            mReportParams = new ArrayList();
        }
        mReportParams.add(reportParam);
    }

    public ReportLayout getReportLayout() {
        return mReportLayout;
    }

    public void setReportLayout(final ReportLayout reportLayout) {
        mReportLayout = reportLayout;
    }

    public ReportDisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(final ReportDisplayType reportDisplayType) {
        displayType = reportDisplayType;
    }

    public void setDisplayType(final String str) {
        displayType = ReportDisplayType.fromDisplayType(str);
    }

    public List<ReportSummary> getSummaries() {
        return summaries;
    }

    public void setSummaries(final List<ReportSummary> list) {
        summaries = list;
    }

    public String getWcfOrderBy() {
        return wcfOrderBy;
    }

    public void setWcfOrderBy(final String str) {
        wcfOrderBy = str;
    }

    public String getOrgSql() {
        return orgSql;
    }

    public void setOrgSql(final String str) {
        orgSql = str;
    }

    public Hashtable<String, String> getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(final String str) {
        if ("" != str) {
            reportDetails = new Hashtable<>();
            for (final String str2 : str.split(";")) {
                final String[] split = str2.split("=");
                reportDetails.put(split[0], split[1]);
            }
        }
    }

    public Hashtable<String, String> getCloneReportDetails() {
        return cloneReportDetails;
    }

    public void setCloneReportDetails(final Hashtable<String, String> hashtable) {
        cloneReportDetails = hashtable;
    }
}
