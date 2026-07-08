package com.sun.activation.registries;

public class MimeTypeEntry {
    private final String extension;
    private final String type;
    public MimeTypeEntry(final String str, final String str2) {
        type = str;
        extension = str2;
    }
    public String getMIMEType() {
        return type;
    }
    public String toString() {
        return "MIMETypeEntry: " + type + ", " + extension;
    }
}
