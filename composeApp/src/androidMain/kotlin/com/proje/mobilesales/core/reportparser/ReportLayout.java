package com.proje.mobilesales.core.reportparser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class ReportLayout {
    private List<ReportAdditionalParam> additionalParams;
    private String application;
    private ReportLayoutViewCard card;
    private ReportChart mChart;
    private List<ReportProperty> mReportProperties = new ArrayList();
    private final List<ReportColumn> mReportColumns = new ArrayList();
    private List<ReportDataType> reportDataTypes = new ArrayList();

    public ReportLayout() {
        this.additionalParams = new ArrayList();
    }

    public List<ReportProperty> getReportProperties() {
        return mReportProperties;
    }

    public List<ReportColumn> getReportColumns() {
        return mReportColumns;
    }

    public void setReportProperties(final List<ReportProperty> list) {
        mReportProperties = list;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(final String str) {
        application = str;
    }

    public ReportChart getChart() {
        return mChart;
    }

    public void setChart(final ReportChart reportChart) {
        mChart = reportChart;
    }

    public ReportLayoutViewCard getCard() {
        return card;
    }

    public void setCard(final ReportLayoutViewCard reportLayoutViewCard) {
        card = reportLayoutViewCard;
    }

    public List<ReportDataType> getReportDataTypes() {
        return reportDataTypes;
    }

    public void setReportDataTypes(final List<ReportDataType> list) {
        reportDataTypes = list;
    }

    public List<ReportAdditionalParam> getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(final List<ReportAdditionalParam> list) {
        additionalParams = list;
    }

    public List<ReportColumn> getVisibleColumns() {
        final List<ReportColumn> list = mReportColumns;
        if (null == list || list.isEmpty()) {
            return new ArrayList();
        }
        return (List) mReportColumns.stream().filter(new Predicate() { // from class: com.proje.mobilesales.core.reportparser.ReportLayoutExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public boolean test(final Object obj) {
                final boolean isVisible;
                isVisible = ((ReportColumn) obj).isVisible();
                return isVisible;
            }
        }).collect(Collectors.toList());
    }
}
