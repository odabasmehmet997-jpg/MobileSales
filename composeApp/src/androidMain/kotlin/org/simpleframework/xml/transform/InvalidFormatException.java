package org.simpleframework.xml.transform;


public class InvalidFormatException extends TransformException {
    public InvalidFormatException(final String str, final Object... objArr) {
        super(String.format(str, objArr));
    }

    public InvalidFormatException(final Throwable th, final String str, final Object... objArr) {
        super(String.format(str, objArr), th);
    }
}
