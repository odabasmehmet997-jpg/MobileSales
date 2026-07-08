package com.proje.mobilesales.features.reports.model;

import com.proje.mobilesales.features.reports.model.enums.ReportSerieViewType;

public final class ReportArgumentValueModel {
    private String mArgumentDataMember;
    private String mValueDataMember;
    public String reportTitle;
    public ReportSerieViewType serieViewType;
    private int xColumnIndex;
    private int yColumnIndex;

    public ReportArgumentValueModel() {
    }

    public ReportArgumentValueModel(final int i2, final String str, final int i3, final String str2, final ReportSerieViewType reportSerieViewType) {
        xColumnIndex = i2;
        mArgumentDataMember = str;
        yColumnIndex = i3;
        mValueDataMember = str2;
        serieViewType = reportSerieViewType;
    }

    public int getxColumnIndex() {
        return xColumnIndex;
    }

    public void setxColumnIndex(final int i2) {
        xColumnIndex = i2;
    }

    public String getmArgumentDataMember() {
        return mArgumentDataMember;
    }

    public void setmArgumentDataMember(final String str) {
        mArgumentDataMember = str;
    }

    public int getyColumnIndex() {
        return yColumnIndex;
    }

    public void setyColumnIndex(final int i2) {
        yColumnIndex = i2;
    }

    public String getmValueDataMember() {
        return mValueDataMember;
    }

    public void setmValueDataMember(final String str) {
        mValueDataMember = str;
    }
}
