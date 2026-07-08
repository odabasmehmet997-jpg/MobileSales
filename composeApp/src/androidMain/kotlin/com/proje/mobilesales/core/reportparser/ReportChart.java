package com.proje.mobilesales.core.reportparser;

import java.util.ArrayList;
import java.util.List;



public class ReportChart {
    private String mAppearanceNameSerializable;
    private ReportChartDiagram mDiagram;
    private String mSelectionMode;
    private ReportChartSerieView mSerieTemplate;
    private List<ReportChartSerie> mSeries;
    private String mSeriesSelectionMode;
    private List<String> titles;

    public String getAppearanceNameSerializable() {
        return mAppearanceNameSerializable;
    }

    public void setAppearanceNameSerializable(final String str) {
        mAppearanceNameSerializable = str;
    }

    public String getSelectionMode() {
        return mSelectionMode;
    }

    public void setSelectionMode(final String str) {
        mSelectionMode = str;
    }

    public String getSeriesSelectionMode() {
        return mSeriesSelectionMode;
    }

    public void setSeriesSelectionMode(final String str) {
        mSeriesSelectionMode = str;
    }

    public List<ReportChartSerie> getSeries() {
        if (null == this.mSeries) {
            mSeries = new ArrayList();
        }
        return mSeries;
    }

    public void setSeries(final List<ReportChartSerie> list) {
        mSeries = list;
    }

    public ReportChartSerieView getSerieTemplate() {
        return mSerieTemplate;
    }

    public void setSerieTemplate(final ReportChartSerieView reportChartSerieView) {
        mSerieTemplate = reportChartSerieView;
    }

    public ReportChartDiagram getDiagram() {
        return mDiagram;
    }

    public void setDiagram(final ReportChartDiagram reportChartDiagram) {
        mDiagram = reportChartDiagram;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(final List<String> list) {
        titles = list;
    }
}
