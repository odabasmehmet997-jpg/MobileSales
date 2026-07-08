package com.proje.mobilesales.features.model;

public enum ModuleNr {
    INVOICE(4),
    CL_CARD(5),
    ITEM(6);
    int mType;
    ModuleNr(final int i2) {
        mType = i2;
    }
    public int getType() {
        return mType;
    }
    public void setType(final int i2) {
        mType = i2;
    }
}
