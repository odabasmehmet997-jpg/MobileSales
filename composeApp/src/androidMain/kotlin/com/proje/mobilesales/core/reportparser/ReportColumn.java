package com.proje.mobilesales.core.reportparser;

import com.proje.mobilesales.features.reports.model.enums.ReportColumnDataType;



public class ReportColumn {
    private int calculatedWidth;
    private ReportColumnDataType columnDataType;
    private String columnEditName;
    private String fieldName;
    private String name;
    private ReportTranslation translation;
    private boolean visible;
    private int visibleIndex;
    private int width;

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        name = str;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String str) {
        fieldName = str;
    }

    public String getColumnEditName() {
        return columnEditName;
    }

    public void setColumnEditName(final String str) {
        columnEditName = str;
    }

    public int getVisibleIndex() {
        return visibleIndex;
    }

    public void setVisibleIndex(final int i2) {
        visibleIndex = i2;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(final boolean z) {
        visible = z;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int i2) {
        width = i2;
    }

    public ReportTranslation getTranslation() {
        return translation;
    }

    public void setTranslation(final ReportTranslation reportTranslation) {
        translation = reportTranslation;
    }

    public int getCalculatedWidth() {
        return calculatedWidth;
    }

    public void setCalculatedWidth(final int i2) {
        calculatedWidth = i2;
    }

    public ReportColumnDataType getColumnDataType() {
        return columnDataType;
    }

    public void setColumnDataType(final ReportColumnDataType reportColumnDataType) {
        columnDataType = reportColumnDataType;
    }
}
