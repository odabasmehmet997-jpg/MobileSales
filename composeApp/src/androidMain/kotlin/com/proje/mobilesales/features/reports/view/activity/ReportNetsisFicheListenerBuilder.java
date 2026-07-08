package com.proje.mobilesales.features.reports.view.activity;

import java.lang.ref.WeakReference;

public class ReportNetsisFicheListenerBuilder {
    private ReportExtractActivity mContent;
    private WeakReference<ReportExtractActivity> mContent0;
    public ReportNetsisFicheListenerBuilder setmContent(ReportExtractActivity mContent) {
        this.mContent = mContent;
        return this;
    }
    public ReportNetsisFicheListenerBuilder setmContent0(WeakReference<ReportExtractActivity> mContent0) {
        this.mContent0 = mContent0;
        return this;
    }
    public ReportExtractActivity.ReportNetsisFicheListener createReportNetsisFicheListener() {
        return new ReportExtractActivity.ReportNetsisFicheListener(mContent);
    }
}