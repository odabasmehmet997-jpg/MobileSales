package org.simpleframework.xml.strategy;


class Contract {
    private final String label;
    private final String length;
    private final String mark;
    private final String refer;

    public Contract(final String str, final String str2, final String str3, final String str4) {
        length = str4;
        label = str3;
        refer = str2;
        mark = str;
    }

    public String getLabel() {
        return label;
    }

    public String getReference() {
        return refer;
    }

    public String getIdentity() {
        return mark;
    }

    public String getLength() {
        return length;
    }
}
