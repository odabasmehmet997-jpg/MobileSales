package com.proje.mobilesales.core.netsis;

public class NetsisDataPrimitive {
    private String mName;
    private Object mValue;
    public NetsisDataPrimitive(String str, Object obj) {
        this.mName = str;
        this.mValue = obj;
    }
    public String getName() {
        return this.mName;
    }
    public void setName(String str) {
        this.mName = str;
    }
    public Object getValue() {
        return this.mValue;
    }
    public void setValue(Object obj) {
        this.mValue = obj;
    }
}
