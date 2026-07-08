package org.simpleframework.xml.convert;

public class ConvertException extends Exception {
    public ConvertException(final String str, final Object... objArr) {
        super(String.format(str, objArr));
    }
}
