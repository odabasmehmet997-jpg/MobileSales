package com.proje.mobilesales.features.model;

public class GenericDataPrimitive {
    String mName;
    Object mValue;
    public GenericDataPrimitive(final String str, final Object obj) {
        mName = str;
        mValue = obj;
    }
    public String getName() {
        return mName;
    }
    public void setName(final String str) {
        mName = str;
    }
    public Object getValue() {
        return mValue;
    }
    public void setValue(final Object obj) {
        mValue = obj;
    }
}
