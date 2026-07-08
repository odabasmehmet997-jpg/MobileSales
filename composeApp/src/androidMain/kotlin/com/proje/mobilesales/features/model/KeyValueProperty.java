package com.proje.mobilesales.features.model;

public class KeyValueProperty {
    String key;
    String value;
    public KeyValueProperty(final String str, final String str2) {
        key = str;
        value = str2;
    }
    public String getKey() {
        return key;
    }
    public void setKey(final String str) {
        key = str;
    }
    public String getValue() {
        return value;
    }
    public void setValue(final String str) {
        value = str;
    }
}
