package com.proje.mobilesales.core.reportparser;

import com.proje.mobilesales.features.reports.model.enums.ReportSerieViewType;



public class ReportChartSerie {
    private String mArgumentDataMember;
    private String mArgumentScaleType;
    private String mName;
    private ReportChartSerieView mSeriesView;
    private String mValueDataMembersSerializable;
    private ReportSerieViewType serieViewType = ReportSerieViewType.Bar;

    public String getName() {
        return mName;
    }

    public void setName(final String str) {
        mName = str;
    }

    public String getArgumentDataMember() {
        return mArgumentDataMember;
    }

    public void setArgumentDataMember(final String str) {
        mArgumentDataMember = str;
    }

    public String getValueDataMembersSerializable() {
        return mValueDataMembersSerializable;
    }

    public void setValueDataMembersSerializable(final String str) {
        mValueDataMembersSerializable = str;
    }

    public String getArgumentScaleType() {
        return mArgumentScaleType;
    }

    public void setArgumentScaleType(final String str) {
        mArgumentScaleType = str;
    }

    public ReportChartSerieView getSeriesView() {
        return mSeriesView;
    }

    public void setSeriesView(final ReportChartSerieView reportChartSerieView) {
        mSeriesView = reportChartSerieView;
    }

    public ReportSerieViewType getSerieViewType() {
        return serieViewType;
    }

    public void setSerieViewType(final ReportSerieViewType reportSerieViewType) {
        serieViewType = reportSerieViewType;
    }
}
