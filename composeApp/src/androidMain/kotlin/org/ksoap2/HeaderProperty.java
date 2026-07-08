package org.ksoap2;

public class HeaderProperty {
    private String key;
    private String value;

    public HeaderProperty(final String str, final String str2) {
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
