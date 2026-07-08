package org.simpleframework.xml.core;


enum MethodType {
    GET(3),
    IS(2),
    SET(3),
    NONE(0);
    private final int prefix;
    MethodType(final int i2) {
        prefix = i2;
    }
    public int getPrefix() {
        return prefix;
    }
}
