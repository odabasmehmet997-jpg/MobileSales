package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.searchdialog.Searchable;

public class SampleModel implements Searchable {
    private String mTitle;
    public boolean isChecked() {
        return false;
    }

    public SampleModel(final String str) {
        mTitle = str;
    }
    public String getTitle() {
        return mTitle;
    }

    public SampleModel setTitle(final String str) {
        mTitle = str;
        return this;
    }
}
