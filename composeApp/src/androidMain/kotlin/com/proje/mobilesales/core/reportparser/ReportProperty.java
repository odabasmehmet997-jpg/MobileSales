package com.proje.mobilesales.core.reportparser;

import java.util.ArrayList;
import java.util.List;



public class ReportProperty {
    private List<ReportProperty> mReportProperties;
    private String name;
    private String value;

    public ReportProperty(final String str) {
        name = str;
    }

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        name = str;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String str) {
        value = str;
    }

    public List<ReportProperty> getReportProperties() {
        return mReportProperties;
    }

    public void setReportProperties(final List<ReportProperty> list) {
        mReportProperties = list;
    }

    public void addProperty(final ReportProperty reportProperty) {
        if (null == this.mReportProperties) {
            mReportProperties = new ArrayList();
        }
        mReportProperties.add(reportProperty);
    }
}
