package com.proje.mobilesales.features.model;

import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsableCampaignCard {

    private String code;
    private boolean isSelected;

    private int logicalRef;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(final String str) {
        code = str;
    }

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        name = str;
    }

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }

    public String getDescription() {
        if (-1 == this.logicalRef) {
            return name;
        }
        String sb = (!TextUtils.isEmpty(code) ? code : "") +
                " - " +
                (TextUtils.isEmpty(name) ? "" : name);
        return sb;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(final boolean z) {
        isSelected = z;
    }
}
